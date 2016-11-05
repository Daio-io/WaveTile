package io.daio.wavetile.api.beach

import android.content.Context
import android.preference.PreferenceManager
import io.daio.wavetile.api.model.Beach
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class BeachStore(context: Context) {

    private val BEACH_STORE_KEY = "BEACH_STORE_KEY"
    private val CACHE_BEACHES_STORE_KEY = "CACHE_BEACH_STORE_KEY"

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun storeBeach(beach: Beach) {
        prefs.edit().putString(BEACH_STORE_KEY, beach.toJsonString()).apply()
    }

    fun getBeach(): Beach? {
        val beach = prefs.getString(BEACH_STORE_KEY, null)

        beach?.let {
            val jsonObj = JSONObject(it)
            return Beach.fromJson(jsonObj)
        }
        return null
    }

    fun storeBeaches(beaches: List<Beach>?) {
        beaches?.let {
            val jsonArray = JSONArray()
            it.forEach {
                val jsonObject = JSONObject()
                jsonObject.put("name", it.name)
                jsonObject.put("id", it.id)
                jsonObject.put("country", it.country)
                jsonObject.put("region", it.region)
                jsonObject.put("selected", it.selected)
                jsonArray.put(jsonObject)
            }

            prefs.edit().putString(CACHE_BEACHES_STORE_KEY, jsonArray.toString()).apply()
        }
    }

    fun clearData() {
        prefs.edit().remove(CACHE_BEACHES_STORE_KEY).apply()
        prefs.edit().remove(BEACH_STORE_KEY).apply()
    }


    fun retrieveBeaches(): List<Beach>? {
        val jsonString = prefs.getString(CACHE_BEACHES_STORE_KEY, null)
        val selectedBeach = getBeach()
        val beaches = ArrayList<Beach>()

        jsonString?.let {
            val jsonArray = JSONArray(jsonString)

            for (i in 0..jsonArray.length() - 1) {
                val jsonObj = jsonArray.getJSONObject(i)
                val name = jsonObj.getString("name")
                val id = jsonObj.getInt("id")
                val selected = id == selectedBeach?.id
                val country = jsonObj.optString("country")
                val region = jsonObj.optString("region")

                beaches.add(Beach(name, id, country, region, selected))
            }
        }
        return beaches
    }

}

