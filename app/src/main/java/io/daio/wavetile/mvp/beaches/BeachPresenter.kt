package io.daio.wavetile.mvp.beaches

import io.daio.wavetile.api.model.Beach
import io.daio.wavetile.mvp.Presenter
import io.daio.wavetile.mvp.View
import io.daio.wavetile.repo.BeachRepo


class BeachPresenter(private val view: View, private val beachRepo: BeachRepo) : Presenter {

    private var beaches: List<Beach>? = null

    init {
        view.setPresenter(this)
    }

    override fun loadData() {
        requestBeaches()
    }

    override fun beachSelected(beach: Beach?) {
        beach?.let {
            beachRepo.saveUserPreferredBeach(it)
            view.beachStored()

            beaches?.forEach {
                it.selected = beach.id === it.id
            }
        }
    }

    override fun search(query: String?) {
        beaches?.filter {
            it.name!!.toLowerCase().contains(query?.toLowerCase() as CharSequence)
        }?.let {
            view.displayBeaches(it)
        }
    }

    private fun requestBeaches() {
        beachRepo.getBeaches {
            it?.let {
                beaches = it
                view.displayBeaches(it)
            }
        }
    }

}