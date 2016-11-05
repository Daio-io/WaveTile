package io.daio.wavetile.tile

import android.service.quicksettings.Tile
import io.daio.wavetile.api.model.SurfData

class WindTileService: BaseSurfTileService() {

    override fun updateTile(beachName: String?, surfData: SurfData?) {

        qsTile.state = Tile.STATE_ACTIVE
        qsTile.label = "$beachName\n${surfData?.weather?.wind} mph"
        qsTile.updateTile()
    }

}