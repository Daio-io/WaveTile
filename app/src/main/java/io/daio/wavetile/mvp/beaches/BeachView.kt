package io.daio.wavetile.mvp.beaches

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import io.daio.wavetile.R
import io.daio.wavetile.api.model.Beach
import io.daio.wavetile.mvp.beaches.adapter.BeachAdapter
import kotlinx.android.synthetic.main.fragment_beaches_view.*

class BeachView: Fragment(), BeachesContract.View, SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private var presenter: BeachesContract.Presenter? = null
    private val adapter = BeachAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): android.view.View? {
        return inflater?.inflate(R.layout.fragment_beaches_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        beach_list_recycler.layoutManager = LinearLayoutManager(context)
        beach_list_recycler.adapter = adapter
        beach_list_recycler.setHasFixedSize(true)
        adapter.selectListener = {
            presenter?.beachSelected(it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        spinner.visibility = android.view.View.VISIBLE
        presenter?.loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setOnQueryTextListener(this)
    }

    //<editor-fold desc="View Interface Methods">
    override fun setPresenter(presenter: BeachesContract.Presenter?) {
        this.presenter = presenter
    }

    override fun displayBeaches(beaches: List<Beach>) {
        adapter.setBeaches(beaches)
        spinner.visibility = android.view.View.GONE
    }

    override fun beachStored() {
        adapter.savedBeachUpdated()
    }

    override fun showLoadError() {
        spinner.visibility = android.view.View.GONE

        view?.let {
            val snackBar = Snackbar.make(it,
                    "There was an error fetching surf spots",
                    Snackbar.LENGTH_INDEFINITE)

            snackBar.setAction("Retry", {
                spinner.visibility = android.view.View.VISIBLE
                presenter?.loadData()
            })
            snackBar.show()
        }
    }
    //</editor-fold>

    override fun onQueryTextSubmit(query: String): Boolean {
        presenter?.search(query)
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        presenter?.search(newText)
        return false
    }

    override fun onClose(): Boolean {
        presenter?.loadData()
        return false
    }

}