package com.example.jetpacksamplev1.ui.compos_view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.jetpacksamplev1.R

@Composable
fun ShowErrorDialog(msg: String,
                    showDialog: Boolean,
                    onDismiss: () -> Unit) {
    if (showDialog) {
    AlertDialog(


        title = {
            Text(
                stringResource(R.string.message),
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onSecondary
            )
        },
        text = {
            Text(
                msg, style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSecondary
            )
        },
        onDismissRequest = {
            onDismiss
        },
        confirmButton = {
            TextButton(onClick =  onDismiss) {
                Text(
                    stringResource(R.string.ok),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSecondary
                )
            }
        },

        dismissButton = { },
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = Color.White
    )
}
}

@Composable
fun Alert(name: String,
          showDialog: Boolean,
          onDismiss: () -> Unit) {
    if (showDialog) {
        AlertDialog(
            title = {
                Text("Title")
            },
            text = {
                Text(text = name)
            },
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onDismiss ) {
                    Text("OK")
                }
            },
            dismissButton = {}
        )
    }
}

@Composable
fun LoadingScreen(
    isLoading: Boolean,
    content: @Composable () -> Unit
) = if (isLoading
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Loading")
            CircularProgressIndicator()
        }
    }
} else {
    content()
}