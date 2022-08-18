package com.example.jetpacksamplev1.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.jetpacksamplev1.main.launchMainScreen
import com.example.jetpacksamplev1.ui.theme.JetPackSampleV1Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    @Inject
    lateinit var splashViewModel: SplashViewModelDefault

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        } //First default splash screen code ,if comment this code only second splash show.
        setContent {
            JetPackSampleV1Theme {
                SplashView(
                    onLauncherComplete = { destination ->
                        when (destination) {
                            LaunchDestination.MAIN_ACTIVITY -> launchMainScreen(context = this)
                            LaunchDestination.ON_BOARDING -> launchMainScreen(context = this)
                        }
                        finish()
                    })
                  }
            }
    }
}

