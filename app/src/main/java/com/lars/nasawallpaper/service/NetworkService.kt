package com.lars.nasawallpaper.service

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.lars.nasawallpaper.models.ImageData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.URL

class NetworkService {

    private val retrofit by lazy {
        val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Throws(IOException::class)
    fun fetchImageData(): ImageData {
        val service = retrofit.create(NasaService::class.java)
        val call = service.fetchImageData()
        // TODO handle error handling
        return try {
            val response = call.execute()
            checkNotNull(response.body())
        } catch (ex: IOException) {
            throw IOException()
        }
    }

    fun getImage(url: String): Bitmap {
        val input = URL(url).openStream()
        Log.i("info", "image url = $url")
        return try {
            BitmapFactory.decodeStream(input)
        } catch (ex: Exception) {
            // TODO avoid loop
            Log.i("Image", "Current image not available, fallback to default image")
            getImage("https://apod.nasa.gov/apod/image/1902/heic1901aTriangulumS.jpg")
        }
    }

    companion object {
        const val API_KEY_VALUE = "25IgVkodCFGnghQgrqSfDSVcu6cirRAIkfJf0EHr"
        const val PATH =
            "https://api.nasa.gov/planetary/apod?api_key=$API_KEY_VALUE&date=2019-02-23"
    }
}
