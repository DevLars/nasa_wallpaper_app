package com.lars.nasawallpaper.ui.main

import android.util.Log
import com.lars.nasawallpaper.service.NetworkService
import com.lars.nasawallpaper.ui.main.MainView
import kotlinx.coroutines.experimental.launch

class Presenter(val view: MainView) {

    private val service: NetworkService = NetworkService()

    fun retrieveWallpaper() {
        launch {
            view.shouldShowProgressBar()
            val data = service.fetchImageData().await()
            Log.i("info", "image url = " + data.url)
            val image = service.getImage(data.hdurl)

            view.shouldUpdateTitle(data.title)
            view.shouldUpdateBackground(image)
            view.shouldHideProgressBar()
        }
    }
}
