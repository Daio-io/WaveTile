package io.daio.wavetile.api.beach

import io.daio.wavetile.api.model.Beach
import retrofit2.Call
import retrofit2.http.GET

interface BeachAPI {
    @GET("/beach")
    fun beaches(): Call<List<Beach>>
}