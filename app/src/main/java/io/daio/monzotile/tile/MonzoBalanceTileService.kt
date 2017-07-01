package io.daio.monzotile.tile

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import io.daio.monzotile.api.MonzoApi
import io.daio.monzotile.network.OKClient
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch

class MonzoBalanceTileService : TileService() {

    private val api = MonzoApi(OKClient.instance)
    private var job: Job? = null

    override fun onClick() {
        val launchIntent = packageManager.getLaunchIntentForPackage("co.uk.getmondo")
        startActivityAndCollapse(launchIntent)
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartListening() {
        super.onStartListening()
        request()
    }

    private fun request() {
        if(job?.isActive == true) return

        job = launch(CommonPool) {
            val balance = api.getBalance().await()
            qsTile.state = Tile.STATE_ACTIVE
            qsTile.label = balance.amountString
            qsTile.updateTile()
        }
    }

}