package fhnw.emoba.blockbuster.data.models

import fhnw.emoba.blockbuster.data.map
import org.json.JSONObject

abstract class PaginationResult<T>(val jsonObject: JSONObject) {
    val page = jsonObject.getInt("page")
    val totalPages = jsonObject.getInt("total_pages")
    val totalResults = jsonObject.getInt("total_results")
    val results =
        jsonObject.getJSONArray("results").map { createItem(it as JSONObject) }.filterNotNull()

    abstract fun createItem(jsonObject: JSONObject): T?
}