package com.himanshoe.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.himanshoe.core.base.PhotoCollectorTheme
import com.himanshoe.splash.composables.SplashUI

class SplashFragment : Fragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return PhotoCollectorTheme(requireContext()) {
            SplashUI()
        }
    }

}