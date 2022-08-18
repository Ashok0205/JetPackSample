package com.example.jetpacksamplev1.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.navigation.fragment.NavHostFragment
import com.example.jetpacksamplev1.MyApplication
import com.example.jetpacksamplev1.R
import com.example.jetpacksamplev1.screen.login_section.login.LoginFragmentDirections
import com.example.jetpacksamplev1.ui.base.BaseAppCompatActivity
import dagger.hilt.android.AndroidEntryPoint


fun launchMainScreen(context: Context) {
    context.startActivity(Intent(context, MainScreen::class.java))
}
@AndroidEntryPoint
class MainScreen : BaseAppCompatActivity<MainViewModel>() {
  //  lateinit var navController: NavController
    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppFullScreenTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        ViewTreeLifecycleOwner.set(window.decorView, this)

      //  navController = findNavController(R.id.main_nav_host_container)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_container) as NavHostFragment?
        val navController = navHostFragment!!.navController

        if(!MyApplication.INSTANCE.preferencesHelper.getLoginResponse().isNullOrEmpty())
            navController.navigate(LoginFragmentDirections.goToHomeFragment())

      //  val viewModel: TestViewModel = ViewModelProviders.of(this).get(TestViewModel::class.java)
        /*viewModel.showDialogLoadingPrivate.observe(this, object : Observer<Boolean?> {
            override fun onChanged(@Nullable isLoading: Boolean?) {
                if (isLoading != null) {
                    Utility.showToast("isLoading--------- $isLoading")
                    if (isLoading) {
                        // hide your progress bar
                    }
                }
            }
        })


        viewModel.showMessageDialog.observe(this, Observer<Any?> {
            LogUtils.logError("setLoader")
            Utility.showToast("lhhkss--------- $it")
        })


        viewModel.showDialogLoadingPrivate.observe(this, Observer<Any?> {
            LogUtils.logError("setLoader")
            Utility.showToast("lhhkss--------- $it")
        })

        viewModel.showDialogLoadingPrivate.observe(this, Observer {
            LogUtils.logError("setLoader")
            Utility.showToast("lkss--------- $it")
        })*/
    }


}