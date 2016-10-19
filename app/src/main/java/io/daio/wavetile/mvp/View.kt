package io.daio.wavetile.mvp

import io.daio.wavetile.api.model.Beach

interface View {
    fun setPresenter(presenter: Presenter?)
    fun displayBeaches(beaches: List<Beach>)
    fun beachStored()
}