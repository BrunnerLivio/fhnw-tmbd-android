package fhnw.emoba.blockbuster.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import fhnw.emoba.blockbuster.data.models.MultiSearchItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class SearchModel(val context: BlockbusterModel) {
    private val _searchQuery: MutableStateFlow<String> =
        MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery
    var searchItems: List<MultiSearchItem> by mutableStateOf(emptyList())
    var isLoading by mutableStateOf(false)
    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @OptIn(FlowPreview::class)
    fun init() {
        modelScope.launch {
            _searchQuery.debounce(timeoutMillis = 500).collectLatest { input ->
                getSearchItemsAsync(input)
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun getSearchItemsAsync(query: String) {
        isLoading = true
        searchItems = emptyList()
        modelScope.launch {
            searchItems = context.tmbdService.multiSearch(query).results
            searchItems.forEach {
                it.image = ImageLoader.loadImage(it.getImagePath()) { path ->
                    context.tmbdService.getPoster(path).asImageBitmap()
                }.await()
            }
            isLoading = false
        }
    }


}