package io.daio.wavetile.api.model


data class SurfData(val date: String?,
                    val time: String?,
                    val solidStar: Int?,
                    val fadedStar: Int?,
                    val weather: WeatherData?,
                    val swell: SwellData?)