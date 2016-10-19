package io.daio.wavetile.api.image

import io.daio.wavetile.api.model.ImageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageAPI {
    @GET("/image")
    fun image(@Query("word") word: String): Call<ImageResponse>
}