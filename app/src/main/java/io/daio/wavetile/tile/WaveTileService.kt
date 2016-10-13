package io.daio.wavetile.tile

import android.service.quicksettings.TileService
import io.daio.wavetile.api.surf.SurfQueryAPI
import java.util.*

class WaveTileService : TileService() {

    private val api = SurfQueryAPI("3EC4931A9C")

    override fun onClick() {
        super.onClick()
        request()
    }

    override fun onStartListening() {
        super.onStartListening()
        request()
    }

    private fun updateTile(minSwell: Int?, maxSwell: Int?) {
        qsTile.label = "Porthcawl\n$minSwell - ${maxSwell}ft"
        qsTile.updateTile()
    }

    private fun request() {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        api.next(1449, hour, {
            val swell = it?.response?.first()?.swell
            updateTile(swell?.minSwell, swell?.maxSwell)
        })
    }

}