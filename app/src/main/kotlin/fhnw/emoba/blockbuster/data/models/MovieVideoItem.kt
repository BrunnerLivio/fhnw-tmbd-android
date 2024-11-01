package fhnw.emoba.blockbuster.data.models

import org.json.JSONObject

class MovieVideoItem(jsonObject: JSONObject) {
    val iso_639_1 = jsonObject.getString("iso_639_1")
    val iso_3166_1 = jsonObject.getString("iso_3166_1")
    val name = jsonObject.getString("name")
    val key = jsonObject.getString("key")
    val site = jsonObject.getString("site")
    val size = jsonObject.getInt("size")
    val type = jsonObject.getString("type")
    val official = jsonObject.getBoolean("official")
    val published_at = jsonObject.getString("published_at")
    val id = jsonObject.getString("id")

}