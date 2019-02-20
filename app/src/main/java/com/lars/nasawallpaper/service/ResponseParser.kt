package com.lars.nasawallpaper.service

import com.google.gson.Gson
import com.lars.nasawallpaper.models.ImageData

class ResponseParser {

    private val gson = Gson()

    fun parseLinkImageResponse(response: String): ImageData = gson.fromJson<ImageData>(response, ImageData::class.java)
}