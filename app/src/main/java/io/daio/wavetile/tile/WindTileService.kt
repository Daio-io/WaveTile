package io.daio.wavetile.tile

import io.daio.wavetile.api.model.SurfData

class WindTileService: BaseSurfTileService() {

    override fun updateTile(beachName: String?, surfData: SurfData?) {

        qsTile.label = "$beachName\n${surfData?.weather?.wind} mph"
        qsTile.updateTile()
    }

}