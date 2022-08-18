package com.example.jetpacksamplev1.screen.login_section.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.jetpacksamplev1.Screen
import com.example.jetpacksamplev1.navigate
import com.example.jetpacksamplev1.network.WebResponse
import com.example.jetpacksamplev1.screen.components.LoadingContent
import com.example.jetpacksamplev1.screen.login_section.login.model.LoginResponse
import com.example.jetpacksamplev1.ui.compos_view.LoadingScreen
import com.example.jetpacksamplev1.ui.compos_view.ShowErrorDialog
import com.example.jetpacksamplev1.ui.theme.JetPackSampleV1Theme
import com.example.jetpacksamplev1.util.LogUtils
import com.example.jetpacksamplev1.util.Utility
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    var response: WebResponse<LoginResponse>? = null
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // return super.onCreateView(inflater, container, savedInstanceState)
        return ComposeView(requireContext()).apply {
            setContent {
                JetPackSampleV1Theme {
                    ProvideWindowInsets{
                        val showDialog = remember { mutableStateOf(false) }
                        val errorMsg = remember { mutableStateOf("") }
                        val isLoading = remember { mutableStateOf(false) }
                        LoadingContent(loading = isLoading.value) {
                            LoginView(
                                requireActivity(), loginViewModel = loginViewModel
                            ) { event ->
                                Utility.hideKeyboard(requireActivity())
                                when (event) {

                                    is LoginEvent.Login -> {
                                        // LogUtils.logError("request email :- "+viewModel.requestLogin.value!!.email)
                                        viewModel.loginApi()
                                        viewModel.loginResponse.observe(
                                            viewLifecycleOwner,
                                            Observer { response ->
                                                when (response) {
                                                    is WebResponse.Success -> {
                                                        navigate(Screen.Home)
                                                    }
                                                    is WebResponse.Error -> {
                                                        errorMsg.value =
                                                            response.errorMessage.toString()
                                                        showDialog.value = true
                                                        isLoading.value = false
                                                    }
                                                    is WebResponse.Loading -> {
                                                        isLoading.value = true

                                                    }
                                                }
                                            })
                                    }
                                    LoginEvent.NavigateBack -> TODO()
                                    LoginEvent.Registration -> TODO()
                                    LoginEvent.ForgotPssword -> navigate(Screen.ForgotPassword)
                                }
                            }
                        }
                        AnimatedVisibility(showDialog.value){
                            if (showDialog.value) {
                                ShowErrorDialog(msg = errorMsg.value,
                                    showDialog = showDialog.value,
                                    onDismiss = {showDialog.value = false})
                            }
                        }


                    }
                }
            }}
    }
}