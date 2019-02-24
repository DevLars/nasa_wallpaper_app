package com.lars.nasawallpaper.ui.main

import android.graphics.Bitmap
import android.util.Log
import com.lars.nasawallpaper.models.ImageData
import com.lars.nasawallpaper.service.NetworkService
import kotlinx.coroutines.experimental.launch

class Presenter(val view: MainView) {

    private val service: NetworkService = NetworkService()

    fun retrieveWallpaper(hdQuality: Boolean) {
        launch {
            view.shouldShowProgressBar()
            val data = service.fetchImageData().await()
            val image = getImage(data, hdQuality)
            view.shouldUpdateTitle(data.title)
            view.shouldUpdateBackground(image)
            view.shouldHideProgressBar()
        }
    }

    private fun getImage(data: ImageData, hdQuality: Boolean): Bitmap {
        return if (hdQuality) {
            service.getImage(data.hdurl)
        } else {
            service.getImage(data.url)
        }
    }
}
