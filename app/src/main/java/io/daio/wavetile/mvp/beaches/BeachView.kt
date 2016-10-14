package io.daio.wavetile.mvp.beaches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import io.daio.wavetile.R
import io.daio.wavetile.api.model.Beach
import io.daio.wavetile.mvp.Presenter
import io.daio.wavetile.mvp.View
import io.daio.wavetile.mvp.beaches.adapter.BeachAdapter
import kotlinx.android.synthetic.main.fragment_beaches_view.*

class BeachView: Fragment(), View {

    private var presenter: Presenter? = null
    private val adapter = BeachAdapter()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): android.view.View? {
        return inflater?.inflate(R.layout.fragment_beaches_view, container, false)
    }

    override fun onViewCreated(view: android.view.View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        beach_list_recycler.layoutManager = LinearLayoutManager(context)
        beach_list_recycler.adapter = adapter
        adapter.selectListener = {
            presenter?.beachSelected(it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter?.start()
    }

    //<editor-fold desc="View Interface Methods">
    override fun setPresenter(presenter: Presenter?) {
        this.presenter = presenter
    }

    override fun displayBeaches(beaches: List<Beach>) {
        adapter.setBeaches(beaches)
    }

    override fun beachStored(beach: Beach) {
        adapter.savedBeachUpdated(beach)
    }

    //</editor-fold>
}