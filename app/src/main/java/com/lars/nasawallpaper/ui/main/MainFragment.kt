package com.lars.nasawallpaper.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import com.lars.nasawallpaper.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.view.updateTitle.observe(this, Observer { title ->
            requireActivity().title = title
        })
        viewModel.view.updateBackground.observe(this, Observer {
            image.setImageBitmap(it)
        })
        viewModel.view.showProgressBar.observe(
            this,
            Observer { progressBar.visibility = View.VISIBLE })
        viewModel.view.hideProgressBar.observe(
            this,
            Observer { progressBar.visibility = View.INVISIBLE })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.presenter.retrieveWallpaper()
    }
}
