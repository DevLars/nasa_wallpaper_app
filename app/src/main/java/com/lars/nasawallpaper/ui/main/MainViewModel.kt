package com.lars.nasawallpaper.ui.main

import android.arch.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val view = MainView()
    val presenter = Presenter(view)
}
