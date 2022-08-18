

package com.example.jetpacksamplev1.screen.states.edittext

import com.example.jetpacksamplev1.MyApplication
import com.example.jetpacksamplev1.R
import java.util.regex.Pattern

// Consider an email valid if there's some text before and after a "@"
private const val EMAIL_VALIDATION_REGEX = "^(.+)@(.+)\$"

class EmailState :
    TextFieldState(validator = ::isEmailValid, errorFor = ::emailValidationError)

/**
 * Returns an error to be displayed or null if no error was found
 */
private fun emailValidationError(email: String): String {
    return if(email.isNullOrEmpty())"Please enter email" else "Invalid email: $email"
}
private fun emailValidationError1(email: String): Int {
    return if(email.isNullOrEmpty()) R.string.email else R.string.email
}

private fun isEmailValid(email: String): Boolean {
    return Pattern.matches(EMAIL_VALIDATION_REGEX, email)
}
