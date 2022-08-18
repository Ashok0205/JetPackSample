package com.example.jetpacksamplev1.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModelDefault @Inject constructor(
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading
    init {
        viewModelScope.launch {
           /* if (true) {
                LauncherViewState(LaunchDestination.ON_BOARDING)
            } else {
                LauncherViewState(LaunchDestination.ON_BOARDING)
            }*/
            _isLoading.value = false
        }
    }

}