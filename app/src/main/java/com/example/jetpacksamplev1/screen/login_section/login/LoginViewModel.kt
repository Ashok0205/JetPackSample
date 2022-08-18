package com.example.jetpacksamplev1.screen.login_section.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jetpacksamplev1.network.APIEndPoint.LOGIN
import com.example.jetpacksamplev1.network.APIEndPoint.USERS
import com.example.jetpacksamplev1.network.ApiCallbacks
import com.example.jetpacksamplev1.network.WebResponse
import com.example.jetpacksamplev1.network.utils.DataError
import com.example.jetpacksamplev1.screen.login_section.login.model.LoginRequests
import com.example.jetpacksamplev1.screen.login_section.login.model.LoginResponse
import com.example.jetpacksamplev1.ui.base.BaseViewModel
import com.example.jetpacksamplev1.util.GsonUtils
import com.example.jetpacksamplev1.util.GsonUtils.toLoginModle
import com.example.jetpacksamplev1.util.LogUtils
import com.example.jetpacksamplev1.util.SOMETHING_WENT_WRONG
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  LoginViewModel @Inject constructor(
    registrationRepo: RegistrationRepo,
) : BaseViewModel<RegistrationRepo>(registrationRepo)  {

    var requestLogin = MutableLiveData<LoginRequests>()
    val loginResponsePrivate = MutableLiveData<WebResponse<LoginResponse>>()
    val loginResponse: LiveData<WebResponse<LoginResponse>> =loginResponsePrivate//get() = loginResponsePrivate

    init {
        onInit()
    }

    fun onInit(){
        requestLogin.value = LoginRequests()
        requestLogin.value!!.email="Developer095@gmail.com"
        requestLogin.value!!.password="123456"
       // getUsers()
    }

    fun onSignInBtnClick(loginRequest: LoginRequests) {
            //GsonUtils.toJosnObject(loginRequest)
     //  callPostWebApi("",GsonUtils.toJosnObject(loginRequest),this)

    }

    fun getUsers()
    {
        viewModelScope.launch(exceptionHandler) {
            getRepo().callGetWebApi(USERS,apiCallbacks)
        }
    }


      fun loginApi() {
         val loginRequest = LoginRequests(
             email = "Developer5@gmail.com",
             password = "123456"
         )

       //  showLoading()
       //   showMessageDialog(DataError(SOMETHING_WENT_WRONG))
      //    Utility.showAlert(SOMETHING_WENT_WRONG)

          viewModelScope.launch(exceptionHandler) {
              loginResponsePrivate.postValue(WebResponse.Loading())
                getRepo().callPostWebApi(LOGIN, GsonUtils.toJosnObject(requestLogin.value!!),apiCallbacks)
          }

     }

    var apiCallbacks: ApiCallbacks =    object :ApiCallbacks{
        override fun onSuccess(jsonResponse: Any?, apiName: String?) {

            when(apiName)
            {
                LOGIN-> loginResponsePrivate.postValue(WebResponse.Success(toLoginModle(jsonResponse!!)))
            }
        }

        override fun onError(errorMsg: String?, apiName: String?,code:Int) {
            LogUtils.logApiResponse("Api Name $apiName :-  $errorMsg")
            when(apiName)
            {
                LOGIN->  loginResponsePrivate.postValue(WebResponse.Error(errorMsg,code))
            }
           // LogUtils.logError(errorMsg.toString())
        }

     }




}