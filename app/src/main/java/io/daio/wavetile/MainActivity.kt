package io.daio.wavetile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

}
