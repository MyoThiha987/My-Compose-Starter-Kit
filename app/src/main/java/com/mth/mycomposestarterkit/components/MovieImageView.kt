package com.mth.mycomposestarterkit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

/**
 * @Author myothiha
 * Created 22/02/2024 at 11:14 AM.
 **/

@Composable
fun <T> MovieImageView(modifier: Modifier = Modifier, data: T) {
    AsyncImage(
        model = data,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun IconTextView(
    modifier: Modifier = Modifier,
    drawableRes: Int,
    text: String,
    textColor: Color
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = drawableRes),
            contentDescription = null,
        )
        Text(
            text = text, fontSize = 14.sp, color = textColor,
            style = TextStyle(
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            ),
        )
    }
}