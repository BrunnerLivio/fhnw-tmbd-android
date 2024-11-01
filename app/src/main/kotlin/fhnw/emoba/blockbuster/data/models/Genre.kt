package fhnw.emoba.blockbuster.data.models

import org.json.JSONObject

class Genre (val jsonObject: JSONObject) {
    val id = jsonObject.getInt("id")
    val name = jsonObject.getString("name")
}