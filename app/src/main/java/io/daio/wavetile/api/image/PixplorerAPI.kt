package io.daio.wavetile.api.image

import io.daio.wavetile.api.model.Image
import io.daio.wavetile.api.model.ImageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PixplorerAPI {

    private val api: ImageAPI

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://api.pixplorer.co.uk/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        api = retrofit.create(ImageAPI::class.java)
    }

    fun getImage(word: String, success: ((Image?) -> Unit)? = null, failure: ((Throwable?) -> Unit)? = null) {

        api.image(word).enqueue(object : Callback<ImageResponse> {
            override fun onResponse(call: Call<ImageResponse>?, response: Response<ImageResponse>?) {
                success?.invoke(response?.body()?.images?.first())
            }

            override fun onFailure(call: Call<ImageResponse>?, t: Throwable?) {
                failure?.invoke(t)
            }

        })

    }

}