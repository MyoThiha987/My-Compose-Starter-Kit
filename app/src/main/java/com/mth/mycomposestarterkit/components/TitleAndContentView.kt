package com.mth.mycomposestarterkit.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mth.mycomposestarterkit.ui.theme.Yellow

/**
 * @Author myothiha
 * Created 22/02/2024 at 11:17 AM.
 **/

@Composable
fun LabelAndContentSection(
    modifier: Modifier = Modifier,
    title: String,
    isSeeAllShowed: Boolean = false,
    content: @Composable () -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(0.8f),
                text = title,
                color = Color.White,
                fontSize = 20.sp,
                lineHeight = 30.sp,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                ),
            )

            AnimatedVisibility(
                visible = isSeeAllShowed,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkHorizontally()
            ) {
                Text(
                    modifier = Modifier.wrapContentSize(),
                    text = "See all",
                    color = Yellow,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                )
            }
        }
        content()
    }

}