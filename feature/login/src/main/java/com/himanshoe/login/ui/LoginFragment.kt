package com.himanshoe.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.himanshoe.core.base.PhotoCollectorTheme
import com.himanshoe.login.composable.LoginUI

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return PhotoCollectorTheme(requireContext()) {
            LoginUI(viewModel)
        }
    }
}