package com.lars.nasawallpaper.service

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.lars.nasawallpaper.models.ImageData
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NetworkService {

    private val parser = ResponseParser()

    fun fetchImageData(): Deferred<ImageData> =
        async {
            val url = URL(PATH)
            val response = StringBuffer()
            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "GET"

                println("\nSending 'GET' request to URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    println(response.toString())
                }
            }
            Log.i("response", response.toString())
            parser.parseLinkImageResponse(response.toString())
        }

    fun getImage(url: String): Bitmap {
        val input = URL(url).openStream()
        Log.i("info", "image url = $url")
        return try {
            BitmapFactory.decodeStream(input)
        } catch (ex: Exception) {
            Log.i("Image", "Current image not available, fallback to default image")
            getImage("https://apod.nasa.gov/apod/image/1902/heic1901aTriangulumS.jpg")
        }
    }

    companion object {
        const val PATH = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=2019-02-23"
        const val API_KEY_NAME = "api_key"
        const val API_KEY_VALUE = "DEMO_KEY"
    }
}
