package io.daio.wavetile.repo

import io.daio.wavetile.api.beach.BeachFinderAPI
import io.daio.wavetile.api.beach.BeachStore
import io.daio.wavetile.api.model.Beach

class BeachRepo(private val beachStore: BeachStore, private val beachFinderAPI: BeachFinderAPI) {

    fun getUserSavedBeach() = beachStore.getBeach()

    fun saveUserPreferredBeach(beach: Beach) = beachStore.storeBeach(beach)

    fun getBeaches(success: (List<Beach>?) -> Unit, failure: () -> Unit) {
        val beaches = beachStore.retrieveBeaches()

        val size = beaches?.size?.compareTo(0)

        when (size) {
            0 -> {
                beachFinderAPI.beaches({
                    beachStore.storeBeaches(it)
                    success(it)
                }, {
                    failure()
                })

            } else -> success(beaches)
        }
    }

}