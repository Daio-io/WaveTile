package io.daio.wavetile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import io.daio.wavetile.api.beach.BeachFinderAPI
import io.daio.wavetile.api.beach.BeachStore
import io.daio.wavetile.mvp.beaches.BeachPresenter
import io.daio.wavetile.mvp.beaches.BeachView
import io.daio.wavetile.repo.BeachRepo

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view = BeachView()
        val beachStore = BeachStore(applicationContext)
        val api = BeachFinderAPI(beachStore)
        val repo = BeachRepo(beachStore, api)
        BeachPresenter(view, repo)

        supportFragmentManager.beginTransaction()
                .replace(R.id.view_container, view)
                .commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.itemId?.compareTo(R.id.action_settings)?.let {
            AboutActivity.start(MainActivity@this)
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        fun startIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            return intent
        }

    }

}
