package com.example.jetpacksamplev1.screen.login_section.forgot_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpacksamplev1.network.APIEndPoint
import com.example.jetpacksamplev1.network.ApiCallbacks
import com.example.jetpacksamplev1.network.CommonServerRes
import com.example.jetpacksamplev1.network.WebResponse
import com.example.jetpacksamplev1.screen.home.model.MoviesResponseItem
import com.example.jetpacksamplev1.screen.login_section.forgot_password.model.ForgotPasswordRequests
import com.example.jetpacksamplev1.screen.login_section.login.RegistrationRepo
import com.example.jetpacksamplev1.screen.login_section.login.model.LoginRequests
import com.example.jetpacksamplev1.screen.login_section.login.model.LoginResponse
import com.example.jetpacksamplev1.ui.base.BaseViewModel
import com.example.jetpacksamplev1.util.GsonUtils
import com.example.jetpacksamplev1.util.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    registrationRepo: RegistrationRepo,
) : BaseViewModel<RegistrationRepo>(registrationRepo)  {


    var request = MutableLiveData<ForgotPasswordRequests>()
    private val serverResponsePrivate = MutableLiveData<WebResponse<CommonServerRes>>()
    val serverResponse: LiveData<WebResponse<CommonServerRes>> =serverResponsePrivate

    init {
        request.value = ForgotPasswordRequests()
        request.value!!.email="Developer095@gmail.com"
    }

    fun callForgotPasswordApi()
    {
        viewModelScope.launch(exceptionHandler) {
            serverResponsePrivate.postValue(WebResponse.Loading())
           // getRepo().callPostWebApi(APIEndPoint.LOGIN, GsonUtils.toJosnObject(request.value!!),apiCallbacks)
        }
    }


    var apiCallbacks: ApiCallbacks =    object : ApiCallbacks {
        override fun onSuccess(jsonResponse: Any?, apiName: String?) {

            when(apiName)
            {
                APIEndPoint.LOGIN -> serverResponsePrivate.postValue(WebResponse.Success(
                    GsonUtils.toCommonServerResponse(
                        jsonResponse!!
                    )
                ))
            }
        }

        override fun onError(errorMsg: String?, apiName: String?,code:Int) {
            LogUtils.logApiResponse("Api Name $apiName :-  $errorMsg")
            when(apiName)
            {
                APIEndPoint.LOGIN ->  serverResponsePrivate.postValue(WebResponse.Error(errorMsg,code))
            }
            // LogUtils.logError(errorMsg.toString())
        }

    }

}