package fhnw.emoba.blockbuster.data.models

import org.json.JSONObject

class PopularPeopleResult(jsonObject: JSONObject) : PaginationResult<PeopleItem>(
    jsonObject
) {
    override fun createItem(jsonObject: JSONObject): PeopleItem = PeopleItem(
        jsonObject
    )

}