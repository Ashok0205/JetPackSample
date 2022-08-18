/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetpacksamplev1

import android.animation.AnimatorSet
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.jetpacksamplev1.screen.login_section.login.LoginFragmentDirections
import com.example.jetpacksamplev1.util.Utility


enum class Screen { Welcome, Login, Registration,ForgotPassword, Home }


fun Fragment.navigate(to: Screen) {
    try {
        when (to) {
            Screen.Welcome -> {
               /// findNavController().navigate(R.id.home_fragment)
                findNavController().navigate(LoginFragmentDirections.goToHomeFragment())
            }
            Screen.Home -> {
                findNavController().navigate(LoginFragmentDirections.goToHomeFragment())
                //  findNavController().navigate(R.id.login_fragment)
            }
            Screen.Login -> {
                val navOptions: NavOptions =  NavOptions.Builder().setPopUpTo(R.id.nav_graph, true).build()
                findNavController().navigate(R.id.loginFragment,null,navOptions)
            }
            Screen.Registration -> {
             //   findNavController().navigate(R.id.login_fragment)
            }
            Screen.ForgotPassword -> {
                findNavController().navigate(LoginFragmentDirections.goToForgotPasswordFragment())
            }

        }
    } catch (e: Exception) {
        Utility.showToast("Can't navigate to $to")
    }
}

/*
fun Fragment.navigate(to: Screen, from: Screen) {
    if (to == from) {
        throw InvalidParameterException("Can't navigate to $to")
    }
    when (to) {
        Screen.Welcome -> {
            findNavController().navigate(R.id.home_fragment)
        }
        Screen.SignUp -> {
            findNavController().navigate(R.id.login_fragment)
        }
        Screen.SignIn -> {
            findNavController().navigate(R.id.login_fragment)
        }
        Screen.Survey -> {
            findNavController().navigate(R.id.login_fragment)
        }
    }
}
*/
