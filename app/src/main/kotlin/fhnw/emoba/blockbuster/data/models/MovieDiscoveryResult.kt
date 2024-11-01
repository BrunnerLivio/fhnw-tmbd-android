package fhnw.emoba.blockbuster.data.models

import org.json.JSONObject

class MovieDiscoveryResult(jsonObject: JSONObject) : PaginationResult<MovieItem>(
    jsonObject
) {
    override fun createItem(jsonObject: JSONObject): MovieItem = MovieItem(
        jsonObject
    )

}