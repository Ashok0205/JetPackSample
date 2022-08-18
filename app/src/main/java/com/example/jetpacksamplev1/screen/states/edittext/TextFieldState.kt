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

package com.example.jetpacksamplev1.screen.states.edittext

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.jetpacksamplev1.util.Utility

open class TextFieldState(
    private val validator: (String) -> Boolean = { true },
    private val errorFor: (String) -> String = { "" }
) {
    var text: String by mutableStateOf("")


    open val isValid: Boolean
        get() = validator(text)

 /*
 // was the TextField ever focused
   var isFocusedDirty: Boolean by mutableStateOf(false)
    var isFocused: Boolean by mutableStateOf(false)
    private var displayErrors: Boolean by mutableStateOf(false)

  fun onFocusChange(focused: Boolean) {
        isFocused = focused
        if (focused) isFocusedDirty = true
    }

    fun enableShowErrors() {
        // only show errors if the text was at least once focused
        if (isFocusedDirty) {
            displayErrors = true
        }
    }*/

    fun showErrors() = !isValid //&& displayErrors

   open fun getError(): String? {
       return if (showErrors()) {
           errorFor(text)
       } else {
           null
       }
   }

    fun getErrorShow(): Boolean {
       if (showErrors()) {
            Utility.showValidation(errorFor(text))
        } else {
            null
        }
        return  showErrors()
    }
     /*fun getErrorShow(): String? {
      ///  Utility.showToast(errorFor(text)+"validator $isValid  text $text")
        return if (showErrors()) {
           Utility.showValidation(errorFor(text))
        } else {
            null
        }
    }*/
}
