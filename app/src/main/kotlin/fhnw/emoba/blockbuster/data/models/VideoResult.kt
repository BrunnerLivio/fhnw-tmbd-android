package fhnw.emoba.blockbuster.data.models

import org.json.JSONObject

class VideoResult(jsonObject: JSONObject): Result<MovieVideoItem>(jsonObject) {
    override fun createItem(jsonObject: JSONObject): MovieVideoItem? {
        return MovieVideoItem(jsonObject)
    }
}