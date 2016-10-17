package io.daio.wavetile.mvp

import io.daio.wavetile.api.model.Beach

interface Presenter {
    fun start()
    fun beachSelected(beach: Beach?)
    fun search(query: String?)
}