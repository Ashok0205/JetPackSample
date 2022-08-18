package com.example.jetpacksamplev1.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpacksamplev1.MyApplication
import com.example.jetpacksamplev1.network.ApiCallbacks
import com.example.jetpacksamplev1.network.utils.ApiResponse
import com.example.jetpacksamplev1.network.utils.DataError
import com.example.jetpacksamplev1.network.utils.Success
import com.example.jetpacksamplev1.util.LogUtils
import com.example.jetpacksamplev1.util.SOMETHING_WENT_WRONG
import com.example.jetpacksamplev1.util.coroutines.DispatcherProvider
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseViewModel<R : BaseRepository>(
    private val repository: R,
) : ViewModel() {
    val loading = MutableStateFlow(false)
    val showDialogLoadingPrivate = MutableLiveData(false)
    val showMessageDialog = MutableLiveData<ApiResponse<String>>()
    private val onErrorDialogDismissPrivate = MutableLiveData<ApiResponse<Boolean>>()
    val onErrorDialogDismiss: LiveData<ApiResponse<Boolean>> get() = onErrorDialogDismissPrivate

    protected val exceptionHandler = CoroutineExceptionHandler { context, exception ->
        hideLoading()
        showMessageDialog(DataError(SOMETHING_WENT_WRONG))

    }


    fun isLoading(): Boolean {
        return showDialogLoadingPrivate.value!!
    }


    fun showLoading() {
        loading.value = true
        if (!showDialogLoadingPrivate.value!!) {
            showDialogLoadingPrivate.value = true
        }

    }

    fun hideLoading() {
        loading.value = false
        if (showDialogLoadingPrivate.value!!) {
            showDialogLoadingPrivate.value = false
        }
    }

    fun getRepo(): R {
        return repository
    }

    fun getAppDispatcher(): DispatcherProvider {
        return MyApplication.INSTANCE.appDispatcherProvider
    }

    fun showMessageDialog(dataError: DataError<String>) {
        showMessageDialog.value = dataError
    }

    fun showPostMessageDialog(dataError: DataError<String>) {
        showMessageDialog.value = dataError
    }

    fun hideMessageDialog(success: Success<String>) {
        showMessageDialog.value = success

    }



}