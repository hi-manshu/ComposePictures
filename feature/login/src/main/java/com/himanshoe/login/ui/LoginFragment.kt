package com.himanshoe.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.himanshoe.core.base.PhotoCollectorTheme
import com.himanshoe.login.R
import com.himanshoe.login.ui.composable.LoginUI
import com.himanshoe.core.util.IResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    companion object {
        private const val RC_SIGN_IN = 100
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var auth: FirebaseAuth
    private val googleSignIn by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }
    private val googleSignInClient by lazy {
        GoogleSignIn.getClient(
            requireContext(),
            googleSignIn
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return PhotoCollectorTheme(requireContext()) {
            LoginUI(viewModel)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
        viewModel.navigator.navigateBy(this)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.initialize.observe(viewLifecycleOwner, {
            auth = Firebase.auth
        })

        viewModel.emailAndPassword.observe(viewLifecycleOwner, { result ->
            when (result) {
                is IResult.OnSuccess -> {
                    loginUser(result.response)
                }
                is IResult.OnFailed -> {
                    Toast.makeText(requireContext(), result.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
        viewModel.idToken.observe(viewLifecycleOwner, { result ->
            when (result) {
                is IResult.OnSuccess -> {
                    val credential = GoogleAuthProvider.getCredential(result.response, null)
                    auth.signInWithCredential(credential)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user = auth.currentUser
                                viewModel.setUser(user)
                            }
                        }
                }
                is IResult.OnFailed -> {
                    Toast.makeText(requireContext(), result.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
        viewModel.loginViaGoogle.observe(viewLifecycleOwner, {
            signInViaGoogle()
        })
    }

    private fun loginUser(response: Pair<String, String>) {
        auth.createUserWithEmailAndPassword(response.first, response.second)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    viewModel.setUser(user)
                }
            }
    }

    private fun signInViaGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                viewModel.getUserFromGoogleId(account.idToken)
            } catch (e: ApiException) {
            }
        }
    }

}