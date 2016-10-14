package io.daio.wavetile.tile

import android.service.quicksettings.TileService
import io.daio.wavetile.api.beach.BeachFinderAPI
import io.daio.wavetile.api.beach.BeachStore
import io.daio.wavetile.api.model.Beach
import io.daio.wavetile.api.surf.SurfQueryAPI
import io.daio.wavetile.repo.BeachRepo
import java.util.*

class WaveTileService : TileService() {

    private val api = SurfQueryAPI("API_KEY")
    private var repo: BeachRepo? = null

    override fun onClick() {
        super.onClick()
        request()
    }

    override fun onCreate() {
        super.onCreate()
        val store = BeachStore(applicationContext)
        repo = BeachRepo(store, BeachFinderAPI(store))
    }

    override fun onStartListening() {
        super.onStartListening()
        request()
    }

    private fun updateTile(beachName: String?, minSwell: Int?, maxSwell: Int?) {
        qsTile.label = "$beachName\n$minSwell - ${maxSwell}ft"
        qsTile.updateTile()
    }

    private fun request() {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val beach = repo?.getUserSavedBeach()

        if (beach is Beach && beach.id is Int) {
            api.next(beach.id, hour, {
                val swell = it?.response?.first()?.swell
                updateTile(beach.name, swell?.minSwell, swell?.maxSwell)
            })
        } else {
            qsTile.label = "Not set"
            qsTile.updateTile()
        }

    }

}