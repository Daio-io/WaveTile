package io.daio.wavetile.tile

import android.service.quicksettings.TileService
import io.daio.wavetile.Constants
import io.daio.wavetile.MainActivity
import io.daio.wavetile.api.beach.BeachFinderAPI
import io.daio.wavetile.api.beach.BeachStore
import io.daio.wavetile.api.model.Beach
import io.daio.wavetile.api.surf.SurfQueryAPI
import io.daio.wavetile.extensions.removeLastWord
import io.daio.wavetile.repo.BeachRepo
import java.util.*

class WindTileService: TileService() {

    private val api = SurfQueryAPI(Constants.API_KEY)
    private var repo: BeachRepo? = null

    override fun onClick() {
        super.onClick()
        request()
        MainActivity.start(applicationContext)
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

    private fun updateTile(beachName: String?, windSpeed: Int?) {
        val name = beachName?.removeLastWord(20)

        qsTile.label = "$name\n${windSpeed}mph"
        qsTile.updateTile()
    }

    private fun request() {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val beach = repo?.getUserSavedBeach()

        if (beach is Beach && beach.id is Int) {
            api.next(beach.id, hour, {
                val wind = it?.response?.first()?.weather?.wind
                updateTile(beach.name, wind)
            })
        } else {
            qsTile.label = "Not set"
            qsTile.updateTile()
        }

    }

}