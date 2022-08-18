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

package com.example.jetpacksamplev1.screen.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpacksamplev1.ui.theme.JetPackSampleV1Theme
import  com.example.jetpacksamplev1.R
import com.example.jetpacksamplev1.ui.theme.Shapes
import okhttp3.internal.wait

@Composable
fun JetPackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = ButtonShape,
    border: BorderStroke? = null,
    //backgroundGradient: List<Color> = JetPackSampleV1Theme.colors.interactivePrimary,
    backgroundGradient: Color = JetPackSampleV1Theme.colors.button_bg,
    disabledBackgroundGradient: List<Color> = JetPackSampleV1Theme.colors.interactiveSecondary,
    contentColor: Color = JetPackSampleV1Theme.colors.textInteractive,
    disabledContentColor: Color = JetPackSampleV1Theme.colors.textHelp,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    JetPackSurface(
        shape = shape,
        color = Color.Transparent,
        contentColor = if (enabled) contentColor else disabledContentColor,
        border = border,
        modifier = modifier
            .clip(shape)
            .background(
                color = backgroundGradient
                /*Brush.horizontalGradient(
                    colors = if (enabled) backgroundGradient else disabledBackgroundGradient
                )*/
            )
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null
            )
    ) {
        ProvideTextStyle(
            value = MaterialTheme.typography.button
        ) {
            Row(
                Modifier
                    .defaultMinSize(
                        minWidth = ButtonDefaults.MinWidth,
                        minHeight = ButtonDefaults.MinHeight
                    )
                    .indication(interactionSource, rememberRipple())
                    .padding(contentPadding),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
    }
}

private val ButtonShape = RoundedCornerShape(percent = 50)

@Preview("default", "round")
@Preview("dark theme", "round", uiMode = UI_MODE_NIGHT_YES)
@Preview("large font", "round", fontScale = 2f)
@Composable
private fun ButtonPreview() {
    JetPackSampleV1Theme {
        JetPackButton(onClick = {}) {
            Text(text = "Demo")
        }
    }
}

@Preview("default", "rectangle")
@Preview("dark theme", "rectangle", uiMode = UI_MODE_NIGHT_YES)
@Preview("large font", "rectangle", fontScale = 2f)
@Composable
private fun RectangleButtonPreview() {
    JetPackSampleV1Theme {
        JetPackButton(
            onClick = {}, shape = RectangleShape
        ) {
            Text(text = "Demo")
        }
    }
}


//---------------------
@Composable
 fun SimpleButton(onSubmit: () -> Unit = {},text: String
        ) {
    Button(
        onClick = onSubmit,
        shape= Shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, bottom = 3.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle2
        )
    }

}
@Composable
fun SimpleOutlinedButton(onSubmit: () -> Unit = {},text: String
) {

    OutlinedButton(
        onClick = onSubmit,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 24.dp)
    ) {
        Surface(color = Color.Yellow) {
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle2
        )
        }
    }
}


@Preview("default")
@Composable
private fun ViewBtn()
{
    SimpleOutlinedButton(onSubmit = {},"Test")
    //SimpleButton(onSubmit = {},"Test")
}