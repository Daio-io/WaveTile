package io.daio.wavetile.tile

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import io.daio.wavetile.Constants
import io.daio.wavetile.MainActivity
import io.daio.wavetile.api.beach.BeachFinderAPI
import io.daio.wavetile.api.beach.BeachStore
import io.daio.wavetile.api.model.Beach
import io.daio.wavetile.api.model.SurfData
import io.daio.wavetile.api.surf.SurfQueryAPI
import io.daio.wavetile.extensions.removeLastWord
import io.daio.wavetile.repo.BeachRepo
import java.util.*

abstract class BaseSurfTileService: TileService() {

    private val api = SurfQueryAPI(Constants.API_KEY)
    private var repo: BeachRepo? = null

    override fun onClick() {
        super.onClick()
        request()
        val intent = MainActivity.startIntent(applicationContext)
        startActivityAndCollapse(intent)
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

    abstract fun updateTile(beachName: String?, surfData: SurfData?)

    private fun request() {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val beach = repo?.getUserSavedBeach()

        if (beach is Beach && beach.id is Int) {
            api.next(beach.id, hour, {
                val surfData = it?.response?.first()
                val name = beach.name.removeLastWord(20)

                updateTile(name, surfData)
            })
        } else {
            qsTile.label = "Tap to setup"
            qsTile.state = Tile.STATE_INACTIVE
            qsTile.updateTile()
        }

    }

}