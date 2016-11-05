package io.daio.wavetile.tile

import android.service.quicksettings.Tile
import io.daio.wavetile.api.model.SurfData

class WaveTileService : BaseSurfTileService() {

    override fun updateTile(beachName: String?, surfData: SurfData?) {
        val swell = surfData?.swell

        qsTile.state = Tile.STATE_ACTIVE
        qsTile.label = "$beachName\n${swell?.minSwell} - ${swell?.maxSwell}ft"
        qsTile.updateTile()
    }

}