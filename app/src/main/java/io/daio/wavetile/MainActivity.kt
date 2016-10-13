package io.daio.wavetile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.daio.wavetile.api.beach.BeachFinderAPI
import io.daio.wavetile.api.model.Beach
import io.daio.wavetile.mvp.Presenter
import io.daio.wavetile.mvp.View
import io.daio.wavetile.mvp.beaches.BeachPresenter
import io.daio.wavetile.mvp.beaches.BeachView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view = BeachView()
        BeachPresenter(view, BeachFinderAPI())

        supportFragmentManager.beginTransaction()
                .replace(R.id.view_container, view)
                .commit()
    }

}
