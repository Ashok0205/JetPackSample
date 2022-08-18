package com.example.jetpacksamplev1.main

import com.example.jetpacksamplev1.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class MainViewModel@Inject constructor( repo: MainRepo):
    BaseViewModel<MainRepo>(repo) {
}