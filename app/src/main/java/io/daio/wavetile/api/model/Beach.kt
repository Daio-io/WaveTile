package io.daio.wavetile.api.model

import org.json.JSONObject

data class Beach(val name: String?,
                 val id: Int?,
                 val country: String?,
                 val region: String?,
                 var selected: Boolean = false) {

    companion object {
        fun fromJson(json: JSONObject): Beach {
            val name = json.getString("name")
            val id = json.getInt("id")
            val country = json.optString("country")
            val region = json.optString("region")

            return Beach(name, id, country, region)
        }
    }

    fun toJsonString(): String {
        val jsonObj = JSONObject()
        jsonObj.put("name", name)
        jsonObj.put("id", id)
        jsonObj.put("country", country)
        jsonObj.put("region", region)
        jsonObj.put("selected", selected)
        return jsonObj.toString()
    }

}