package com.example.jetpacksamplev1.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpacksamplev1.main.MainRepo
import com.example.jetpacksamplev1.network.APIEndPoint
import com.example.jetpacksamplev1.network.ApiCallbacks
import com.example.jetpacksamplev1.network.WebResponse
import com.example.jetpacksamplev1.screen.home.model.MoviesResponseItem
import com.example.jetpacksamplev1.screen.home.model.UserResponseItem
import com.example.jetpacksamplev1.screen.login_section.login.model.LoginRequests
import com.example.jetpacksamplev1.screen.login_section.login.model.LoginResponse
import com.example.jetpacksamplev1.ui.base.BaseViewModel
import com.example.jetpacksamplev1.util.GsonUtils
import com.example.jetpacksamplev1.util.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  HomeViewModel @Inject constructor( repo: MainRepo): BaseViewModel<MainRepo>(repo)
{
    private val userResponsePrivate = MutableLiveData<WebResponse<List<MoviesResponseItem>>>()
    val userResponse: LiveData<WebResponse<List<MoviesResponseItem>>> =userResponsePrivate//get() = loginResponsePrivate
    init {
        onInit()
    }

    fun onInit(){
    }
    fun getMovies()
    {
        viewModelScope.launch(exceptionHandler) {
            getRepo().getMovieWebApi(APIEndPoint.USERS,apiCallbacks)
        }
    }


    var apiCallbacks: ApiCallbacks =    object : ApiCallbacks {
        override fun onSuccess(jsonResponse: Any?, apiName: String?) {
            LogUtils.logApiResponse("Api onSuccess $apiName :-  ${jsonResponse.toString()}")
            when(apiName)
            {
                APIEndPoint.USERS -> userResponsePrivate.postValue(WebResponse.Success(jsonResponse as List<MoviesResponseItem>?))
            }
        }

        override fun onError(errorMsg: String?, apiName: String?,code:Int) {
            LogUtils.logApiResponse("Api Name $apiName :-  $errorMsg")
            when(apiName)
            {
                APIEndPoint.USERS ->  userResponsePrivate.postValue(WebResponse.Error(errorMsg,code))
            }
            // LogUtils.logError(errorMsg.toString())
        }

    }

}