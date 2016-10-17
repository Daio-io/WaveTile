package io.daio.wavetile.mvp.beaches

import io.daio.wavetile.api.model.Beach
import io.daio.wavetile.mvp.Presenter
import io.daio.wavetile.mvp.View
import io.daio.wavetile.repo.BeachRepo


class BeachPresenter(val view: View, val beachRepo: BeachRepo) : Presenter {

    private var beaches: List<Beach>? = null

    init {
        view.setPresenter(this)
    }

    override fun start() {
        beachRepo.getBeaches {
            it?.let {
                beaches = it
                view.displayBeaches(it)
            }
        }
    }

    override fun beachSelected(beach: Beach?) {
        beach?.let {
            beachRepo.saveUserPreferredBeach(it)
            view.beachStored(beach)
        }
    }


    override fun search(query: String?) {
        beaches?.filter {
            it.name!!.toLowerCase().contains(query?.toLowerCase() as CharSequence)
        }?.let {
            view.displayBeaches(it)
        }
    }

}