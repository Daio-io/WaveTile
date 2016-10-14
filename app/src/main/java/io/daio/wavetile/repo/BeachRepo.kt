package io.daio.wavetile.repo

import io.daio.wavetile.api.beach.BeachFinderAPI
import io.daio.wavetile.api.beach.BeachStore
import io.daio.wavetile.api.model.Beach

class BeachRepo(private val beachStore: BeachStore, private val beachFinderAPI: BeachFinderAPI) {

    fun getUserSavedBeach() = beachStore.getBeach()

    fun saveUserPreferredBeach(beach: Beach) = beachStore.storeBeach(beach)

    fun getBeaches(callback: (List<Beach>?) -> Unit) {
        val beaches = beachStore.retrieveBeaches()

        val size = beaches?.size?.compareTo(0)

        when (size) {
            0 -> {
                beachFinderAPI.beaches({
                    beachStore.storeBeaches(it)
                    callback(it)
                })

            } else -> callback(beaches)
        }
    }

}