package io.daio.wavetile.api.beach

import android.util.Log
import io.daio.wavetile.api.model.Beach
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class BeachFinderAPI {

        private val api: BeachAPI

        init {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://beach-suggest.herokuapp.com")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()

            api = retrofit.create(BeachAPI::class.java)
        }

        fun beaches(success: ((List<Beach>?) -> Unit)? = null, failure: ((Throwable?) -> Unit)? = null) {
            api.beaches().enqueue(object: Callback<List<Beach>> {

                override fun onFailure(call: Call<List<Beach>>?, t: Throwable?) {
                    failure?.invoke(t)
                }

                override fun onResponse(call: Call<List<Beach>>?, response: Response<List<Beach>>?) {
                    success?.invoke(response?.body())
                }

            })
        }

}