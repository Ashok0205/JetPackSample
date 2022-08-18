package com.example.jetpacksamplev1.screen.login_section.login

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.example.jetpacksamplev1.R
import com.example.jetpacksamplev1.Screen
import com.example.jetpacksamplev1.network.WebResponse
import com.example.jetpacksamplev1.network.utils.DataError
import com.example.jetpacksamplev1.screen.components.*
import com.example.jetpacksamplev1.screen.login_section.login.model.LoginResponse
import com.example.jetpacksamplev1.screen.states.edittext.EmailState
import com.example.jetpacksamplev1.screen.states.edittext.PasswordState
import com.example.jetpacksamplev1.ui.theme.JetPackSampleV1Theme
import com.example.jetpacksamplev1.ui.theme.button_top
import com.example.jetpacksamplev1.ui.theme.db10
import com.example.jetpacksamplev1.util.Utility
import com.example.jetpacksamplev1.navigate
import com.example.jetpacksamplev1.ui.compos_view.*
import com.example.jetpacksamplev1.util.LogUtils
import kotlinx.coroutines.launch

sealed class LoginEvent {
   // data class Login(val email: String, val password: String) : LoginEvent()
   object Login : LoginEvent()
    object Registration : LoginEvent()
    object ForgotPssword : LoginEvent()
    object NavigateBack : LoginEvent()
}

lateinit var mActivity: FragmentActivity
lateinit  var onLoginEvent: (LoginEvent) -> Unit
lateinit var emailState: EmailState
lateinit var passwordState: PasswordState
lateinit var viewModel: LoginViewModel

@Composable
fun LoginView(
    context: FragmentActivity,
    loginViewModel: LoginViewModel,
    onNavigationEvent: (LoginEvent) -> Unit
){
     mActivity=context
     onLoginEvent=onNavigationEvent
     viewModel=loginViewModel
     emailState = remember { EmailState() }
     passwordState = remember { PasswordState() }

    emailState.text=viewModel.requestLogin.value!!.email
    passwordState.text=viewModel.requestLogin.value!!.password
    onInitView(onNavigationEvent)


    /*LoadingContent(loading) {
        Scaffold(
            backgroundColor = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize(),
            topBar = { HomeTopBar { } }) {
            HeaderTitle()
        }
        setData()

    }
*/

//viewModel.loginApi()
    //Utility.ShowErrorDialog(SOMETHING_WENT_WRONG)
  //  Utility.serverResponse(SOMETHING_WENT_WRONG)
}
@Composable
fun onInitView( onNavigationEvent: (LoginEvent) -> Unit) {
    val loading by viewModel.loading.collectAsState()
    val data by viewModel.loginResponse.observeAsState()
    val showDialog = remember { mutableStateOf(false) }

    var isShowErrro by remember { mutableStateOf(false) }
    LoadingContent(loading) {
    JetPackScaffold(
        content = {
            Column(modifier =Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column() {
                        LogoTitle()
                       // Text(text = "Example", fontSize = 44.sp)
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(8.dp),
                    contentAlignment = Alignment.TopCenter
                ){
                    Column {
                        SignInContent(
                            onSignInSubmitted = {
                              //  Utility.hideKeyboard(mActivity)
                                onSubmit()

                              /*  data.let {
                                    when(it)
                                    {
                                        is WebResponse.Error->{
                                            showDialog.value=true
                                            LogUtils.logError("lokendr here")
                                        }
                                        is WebResponse.Success -> {
                                            onLoginEvent(LoginEvent.Login)
                                            //navigate(Screen.Home)
                                        }
                                    }
                                }*/
                                //  onNavigationEvent(LoginEvent.Login(email, password))
                            }
                        )

                       /* AnimatedVisibility(showDialog.value){
                        if (showDialog.value) {
                            ShowErrorDialog(msg = data?.errorMessage.toString(),
                                showDialog = showDialog.value,
                                onDismiss = {showDialog.value = false})
                        }
                        }*/

                        Spacer(modifier = Modifier.height(db10))
                    }
                }
            }

                ///---------

           /* Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(10.dp),

            ) {
                item {



                }
            }}*/

        }
    )
}

}


@Composable
fun SignInContent(
    onSignInSubmitted: () -> Unit,
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        val focusRequester = remember { FocusRequester() }
        Email(emailState, onImeAction = { focusRequester.requestFocus() })
        Spacer(modifier = Modifier.height(16.dp))

        Password(
            label = stringResource(id = R.string.password),
            passwordState = passwordState,
            modifier = Modifier.focusRequester(focusRequester),
            onImeAction = { onSignInSubmitted() }
        )
        TextButton(onClick = { onLoginEvent(LoginEvent.ForgotPssword)}) {
            Text(
                stringResource(R.string.forgot_password),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSecondary
            )
        }
       /* ClickableText(
            text = AnnotatedString(stringResource(id = R.string.forgot_password)) ,
            onClick = {
                onLoginEvent(LoginEvent.ForgotPssword)
            })*/
        TextButton(
            onClick = {
                onLoginEvent(LoginEvent.ForgotPssword)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.forgot_password))
        }
        Spacer(modifier = Modifier.height(button_top))
        JetPackButton(
            onClick = {
                onSignInSubmitted()
            },
            // shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth(),
            //  enabled = emailState.isValid && passwordState.isValid,
            contentPadding = PaddingValues(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.login),
                style = MaterialTheme.typography.button.copy(color = JetPackSampleV1Theme.colors.button_txt),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
      //  SimpleButton(onSubmit = {}, text = stringResource(id = R.string.login))
       // SimpleOutlinedButton(onSubmit = {}, text = stringResource(id = R.string.login))
    }



}

fun isValid():Boolean
{  if(emailState.getErrorShow())
      return false
   else if(passwordState.getErrorShow())
        return false
    return true
}

fun onSubmit()
{
    if(isValid()) {

        viewModel.requestLogin.value!!.email = emailState.text
        viewModel.requestLogin.value!!.password = passwordState.text
      ///  viewModel.loginApi()
        onLoginEvent(LoginEvent.Login)
        // onNavigationEvent(LoginEvent.Login(email, password))


    }

}

@Composable
fun  setData(){
    Utility.showToast("lokendra")
}

/*@Preview(name = "Sign in light theme")
@Composable
fun SignInPreview() {
    JetPackSampleV1Theme {
        LoginView( onNavigationEvent ={} )
    }
}

@Preview(name = "Sign in dark theme")*/
@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SignInPreviewDark() {
    JetPackSampleV1Theme {
        onInitView( onNavigationEvent ={} )
    }
}
