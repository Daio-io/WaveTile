package io.daio.wavetile.mvp.beaches

import io.daio.wavetile.api.model.Beach

interface BeachesContract {

    interface Presenter {
        fun loadData()
        fun beachSelected(beach: Beach?)
        fun search(query: String)
    }

    interface View {
        fun setPresenter(presenter: Presenter?)
        fun displayBeaches(beaches: List<Beach>)
        fun beachStored()
        fun showLoadError()
    }
}