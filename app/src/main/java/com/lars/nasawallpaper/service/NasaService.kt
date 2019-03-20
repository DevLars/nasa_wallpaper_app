package com.lars.nasawallpaper.service

import com.lars.nasawallpaper.models.ImageData
import retrofit2.Call
import retrofit2.http.GET

interface NasaService {

    @GET("planetary/apod?api_key=25IgVkodCFGnghQgrqSfDSVcu6cirRAIkfJf0EHr&date=2019-02-23")
    fun fetchImageData(): Call<ImageData>
}
