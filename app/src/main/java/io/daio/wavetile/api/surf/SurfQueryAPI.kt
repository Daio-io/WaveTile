package io.daio.wavetile.api.surf

import io.daio.wavetile.api.model.SurfResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.xml.datatype.DatatypeConstants.SECONDS



class SurfQueryAPI(val apiKey: String) {

    private val api: SurfAPI

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://surf-query.herokuapp.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        api = retrofit.create(SurfAPI::class.java)
    }

    fun next(spotId: Int, startTime: Int?,
             success: ((SurfResponse?) -> Unit)? = null,
             failure: ((Throwable?) -> Unit)? = null) {

        api.next(spotId, startTime, apiKey).enqueue(object: Callback<SurfResponse> {

            override fun onFailure(call: Call<SurfResponse>?, t: Throwable?) {
                failure?.invoke(t)
            }

            override fun onResponse(call: Call<SurfResponse>?, response: Response<SurfResponse>?) {
                success?.invoke(response?.body())
            }

        })
    }

}