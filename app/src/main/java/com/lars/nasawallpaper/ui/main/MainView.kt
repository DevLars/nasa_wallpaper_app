package com.lars.nasawallpaper.ui.main

import android.arch.lifecycle.MutableLiveData
import android.graphics.Bitmap

class MainView {
    val updateTitle = MutableLiveData<String>()
    val updateBackground = MutableLiveData<Bitmap>()
    val showProgressBar = MutableLiveData<Unit>()
    val hideProgressBar = MutableLiveData<Unit>()

    fun shouldHideProgressBar() = hideProgressBar.postValue(Unit)

    fun shouldShowProgressBar() = showProgressBar.postValue(Unit)

    fun shouldUpdateTitle(title: String) {
        updateTitle.postValue(title)
    }

    fun shouldUpdateBackground(image: Bitmap) {
        updateBackground.postValue(image)
    }
}
