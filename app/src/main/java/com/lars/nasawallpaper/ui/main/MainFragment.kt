package com.lars.nasawallpaper.ui.main

import android.app.WallpaperManager
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.lars.nasawallpaper.R
import kotlinx.android.synthetic.main.main_fragment.*
import java.io.IOException

class MainFragment : Fragment() {

    private var backgroundImage: Bitmap? = null

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.view.updateTitle.observe(this, Observer { title ->
            requireActivity().title = title
        })
        viewModel.view.updateBackground.observe(this, Observer { retrievedImage ->
            backgroundImage = checkNotNull(retrievedImage)
            image.setImageBitmap(retrievedImage)
        })
        viewModel.view.showProgressBar.observe(
            this,
            Observer<Unit?> {
                image.setImageBitmap(null)
                progressBar.visibility = View.VISIBLE
            })
        viewModel.view.hideProgressBar.observe(
            this,
            Observer<Unit?> { progressBar.visibility = View.INVISIBLE })

        viewModel.presenter.retrieveWallpaper(
            PreferenceManager.getDefaultSharedPreferences(context).getBoolean(
                HD_QUALITY,
                false
            )
        )

        fab.setOnClickListener {
            if (backgroundImage != null) {
                val wallpaperManager = WallpaperManager.getInstance(context)
                try {
                    wallpaperManager.setBitmap(backgroundImage)
                    Toast.makeText(context, "Image set as background", Toast.LENGTH_SHORT).show()
                } catch (ex: IOException) {
                    // NOOP
                }
            }
        }
    }

    companion object {
        private const val HD_QUALITY = "hd"
    }
}
