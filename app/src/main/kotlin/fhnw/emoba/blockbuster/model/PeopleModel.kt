package fhnw.emoba.blockbuster.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import fhnw.emoba.blockbuster.data.models.PeopleItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class PeopleModel(context: BlockbusterModel) : ScreenModel(context) {
    var peopleItems: List<PeopleItem> by mutableStateOf(emptyList())
    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun init() {
        getPeopleAsync();
    }

    fun getPeopleAsync() {
        isLoading = true
        peopleItems = emptyList()
        modelScope.launch {
            peopleItems = context.tmbdService.popularPeople().results
            peopleItems.forEach {
                it.profileImage = ImageLoader.loadImage(it.profilePath) { path ->
                    context.tmbdService.getPoster(path).asImageBitmap()
                }.await()
            }
            isLoading = false
        }
    }


}