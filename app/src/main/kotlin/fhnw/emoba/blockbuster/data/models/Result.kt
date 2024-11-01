package fhnw.emoba.blockbuster.data.models

import fhnw.emoba.blockbuster.data.map
import org.json.JSONObject

abstract class Result<T>(val jsonObject: JSONObject) {
    val id = jsonObject.getInt("id")
    val results =
        jsonObject.getJSONArray("results").map { createItem(it as JSONObject) }.filterNotNull()

    abstract fun createItem(jsonObject: JSONObject): T?
}