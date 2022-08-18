package com.example.jetpacksamplev1.ui.compos_view

import android.media.tv.TvContract
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacksamplev1.R
import com.example.jetpacksamplev1.ui.compos_view.Logo
import com.example.jetpacksamplev1.ui.theme.db10
import com.example.jetpacksamplev1.ui.theme.screen_logo_b

@Preview("default")
@Composable
 fun LogoTitle(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.wrapContentHeight(align = Alignment.CenterVertically)
    ) {
       Logo(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 1.dp)
        )
        Text(
            text = stringResource(id = R.string.login),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 18.dp)
                .fillMaxWidth()
        )
       // Spacer(modifier = Modifier.height(screen_logo_b))
    }
}

@Preview("default")
@Composable
fun SimpleButton(onSubmit: () -> Unit = {}) {
    Button(
        onClick = onSubmit,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, bottom = 3.dp)
    ) {
        Text(
            text = stringResource(id = R.string.login),
            style = MaterialTheme.typography.subtitle2
        )
    }
}


