package com.example.jetpacksamplev1.di

import com.example.jetpacksamplev1.network.ApiCallbacks
import com.example.jetpacksamplev1.network.PaymentApiInterface
import com.example.jetpacksamplev1.screen.login_section.login.RegistrationRepo
import com.example.jetpacksamplev1.ui.base.BaseRepository
import com.example.jetpacksamplev1.util.PREF_NAME
import com.example.jetpacksamplev1.util.coroutines.AppDispatcherProvider
import com.example.jetpacksamplev1.util.coroutines.DispatcherProvider
import com.example.jetpacksamplev1.util.prefs.AppPreferencesHelper
import com.example.jetpacksamplev1.util.prefs.PreferencesHelper
import dagger.Provides
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 class AppModule {


    @Provides
    @PreferenceInfo
    fun providePreferenceName(): String {
        return PREF_NAME
    }


    @Provides
    @Singleton
    fun providePreferenceHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper {
    return appPreferencesHelper
    }


    @Provides
    @Singleton
    fun provideDispatcher(dispatcherProvider: AppDispatcherProvider): DispatcherProvider {
    return dispatcherProvider
    }



    @Provides
    @Singleton
    fun provideRegistrationRepo(registrationRepo: RegistrationRepo): BaseRepository {
    return registrationRepo
    }



  /*  @Provides
    @Singleton
    fun provideDashboardRepo(dashboardRepo: DashboardRepo): BaseRepository {
        return dashboardRepo
    }
*/
}