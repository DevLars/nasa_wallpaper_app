package com.lars.nasawallpaper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lars.nasawallpaper.ui.main.MainFragment

class mainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

}
