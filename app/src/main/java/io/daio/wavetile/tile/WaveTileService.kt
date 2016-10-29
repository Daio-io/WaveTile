package io.daio.wavetile.tile

import io.daio.wavetile.api.model.SurfData

class WaveTileService : BaseSurfTileService() {

    override fun updateTile(beachName: String?, surfData: SurfData?) {
        val swell = surfData?.swell

        qsTile.label = "$beachName\n${swell?.minSwell} - ${swell?.maxSwell}ft"
        qsTile.updateTile()
    }

}