package com.lars.nasawallpaper.ui.main

import android.graphics.Bitmap
import com.lars.nasawallpaper.models.ImageData
import com.lars.nasawallpaper.service.NetworkService
import kotlinx.coroutines.experimental.launch

class Presenter(val view: MainView) {

    private val service = NetworkService()
    private var hdImage: Bitmap? = null
    private var image: Bitmap? = null

    fun retrieveWallpaper(hdQuality: Boolean) {
        if (isImageCached(hdQuality)) {
            when (hdQuality) {
                true -> view.shouldUpdateBackground(requireNotNull(hdImage))
                false -> view.shouldUpdateBackground(requireNotNull(image))
            }
        } else {
            launch {
                view.shouldShowProgressBar()
                val data = service.fetchImageData()
                val image = fetchImage(data, hdQuality)
                view.shouldUpdateTitle(data.title)
                view.shouldUpdateBackground(image)
                view.shouldHideProgressBar()
            }
        }
    }

    private fun isImageCached(hdQuality: Boolean): Boolean {
        return if (hdQuality) {
            hdImage != null
        } else {
            image != null
        }
    }

    private fun fetchImage(data: ImageData, hdQuality: Boolean): Bitmap {
        return if (hdQuality) {
            hdImage = service.getImage(data.hdurl)
            hdImage as Bitmap
        } else {
            image = service.getImage(data.url)
            image as Bitmap
        }
    }
}
