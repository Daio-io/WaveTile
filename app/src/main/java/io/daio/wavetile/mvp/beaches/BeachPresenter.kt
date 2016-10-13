package io.daio.wavetile.mvp.beaches

import io.daio.wavetile.api.beach.BeachFinderAPI
import io.daio.wavetile.mvp.Presenter
import io.daio.wavetile.mvp.View


class BeachPresenter(val view: View, val beachApi: BeachFinderAPI): Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {
        beachApi.beaches({
            it?.let { beaches ->
                view.displayBeaches(beaches)
            }
        })
    }

}