package fhnw.emoba.blockbuster.data.models

import org.json.JSONObject

class MultiSearchResult(jsonObject: JSONObject) : PaginationResult<MultiSearchItem>(
    jsonObject
) {
    override fun createItem(jsonObject: JSONObject): MultiSearchItem? {
        val mediaType = jsonObject.getString("media_type")
        if(mediaType == "person") {
            return PeopleItem(jsonObject)
        } else if(mediaType == "movie") {
            return MovieItem(jsonObject)
        }
        return null
    }

}