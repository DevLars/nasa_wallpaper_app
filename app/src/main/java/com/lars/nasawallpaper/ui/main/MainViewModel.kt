package com.lars.nasawallpaper.ui.main

import android.arch.lifecycle.ViewModel
import com.lars.nasawallpaper.Presenter

class MainViewModel : ViewModel() {
    val view = MainView()
    val presenter = Presenter(view)
}
