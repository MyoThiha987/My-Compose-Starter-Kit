package com.mth.mycomposestarterkit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mth.mycomposestarterkit.R

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
    imageRes: Int = R.drawable.cgv,
    text: String,
    textColor: Color,
    iconTintColor: Color = Color.White,
    containsImage: Boolean = false,
    containSubText: Boolean = false,
    fontSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = drawableRes),
            contentDescription = null,
            tint = iconTintColor
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = text,
                    fontSize = fontSize,
                    color = textColor,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    ),
                    fontWeight = fontWeight
                )
                if (containsImage) Image(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                )
            }

            if (containSubText)
                Text(
                    text = text, fontSize = 14.sp, color = textColor,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    ),
                )
        }
    }
}