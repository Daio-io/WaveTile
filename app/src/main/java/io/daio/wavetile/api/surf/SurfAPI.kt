package io.daio.wavetile.api.surf

import io.daio.wavetile.api.model.SurfResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface SurfAPI {

    @GET("/next")
    fun next(@Query("spotid") spotId: Int?, @Query("start") startTime: Int?, @Query("apikey") apikey: String): Call<SurfResponse>
}