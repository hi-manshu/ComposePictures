package com.himanshoe.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.himanshoe.core.base.photoCollectorTheme
import com.himanshoe.core.util.IResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment() {

    private val viewModel: EditProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return photoCollectorTheme(requireContext()) {
            val source = arguments?.getString("source", "GOOGLE") as String
            EditUI(source, viewModel)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator.navigateBy(this)
        viewModel.init()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.userSave.observe(viewLifecycleOwner, { result ->
            when (result) {
                is IResult.OnFailed -> Toast.makeText(
                    requireContext(),
                    result.throwable.message,
                    Toast.LENGTH_SHORT
                ).show()
                is IResult.OnSuccess -> {
                    Toast.makeText(
                        requireContext(),
                        result.response,
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.navigateBack()
                }
            }
        })
    }


}