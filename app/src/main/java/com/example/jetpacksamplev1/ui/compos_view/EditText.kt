package com.example.jetpacksamplev1.ui.compos_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.jetpacksamplev1.screen.states.edittext.EmailState
import com.example.jetpacksamplev1.screen.states.edittext.TextFieldState
import com.example.jetpacksamplev1.R
import com.example.jetpacksamplev1.ui.theme.JetPackSampleV1Theme
import com.example.jetpacksamplev1.util.Utility




@Composable
fun Email(
    emailState: TextFieldState = remember { EmailState() },
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {}
) {
    OutlinedTextField(
        value = emailState.text,
        onValueChange = {
            emailState.text = it
        },
        label = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = stringResource(id = R.string.email),
                    style = MaterialTheme.typography.body2.copy(color = JetPackSampleV1Theme.colors.textSecondary)
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = JetPackSampleV1Theme.colors.outlinedFocused,
            unfocusedBorderColor = JetPackSampleV1Theme.colors.outlinedUnfocused),
        modifier = Modifier
            .fillMaxWidth()
            /*.onFocusChanged { focusState ->
                emailState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    emailState.enableShowErrors()
                }
            }*/,
        textStyle = MaterialTheme.typography.body2.copy( color =  JetPackSampleV1Theme.colors.textSecondary),
        //isError = emailState.showErrors(),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
            }
        )
    )

   // Utility.showToast()
   // emailState.getError()?.let { error -> Utility.showToast( error) }
}



@Composable
fun Password(
    label: String,
    passwordState: TextFieldState,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {}
) {
    val showPassword = remember { mutableStateOf(false) }
    OutlinedTextField(
        value = passwordState.text,
        onValueChange = {
            passwordState.text = it
        //  passwordState.enableShowErrors()
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = JetPackSampleV1Theme.colors.outlinedFocused,
            unfocusedBorderColor = JetPackSampleV1Theme.colors.outlinedUnfocused),
        modifier = modifier
            .fillMaxWidth()
           /* .onFocusChanged { focusState ->
                passwordState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    passwordState.enableShowErrors()
                }
            }*/,
        textStyle = MaterialTheme.typography.body2,
        label = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.body2.copy(color = JetPackSampleV1Theme.colors.textSecondary)
                )
            }
        },
        trailingIcon = {
            if (showPassword.value) {
                IconButton(onClick = { showPassword.value = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = stringResource(id = R.string.hide_password),
                        tint = JetPackSampleV1Theme.colors.textInteractive
                    )
                }
            } else {
                IconButton(onClick = { showPassword.value = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = stringResource(id = R.string.show_password),
                        tint = JetPackSampleV1Theme.colors.textInteractive
                    )
                }
            }
        },
        visualTransformation = if (showPassword.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
      //  isError = passwordState.showErrors(),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
            }
        )
    )
   // passwordState .getError()?.let { error -> Utility.showToast(error) }
 // passwordState.getError()?.let { error -> TextFieldError(textError = error) }
}