package com.mth.mycomposestarterkit.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mth.mycomposestarterkit.ui.theme.Black

/**
 * @Author myothiha
 * Created 26/02/2024 at 4:05 PM.
 **/
@Composable
fun DashLine(modifier: Modifier=Modifier) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    Canvas(
        modifier=modifier.fillMaxWidth()
    ) {
        drawLine(
            color = Black.copy(alpha = 0.3f ),
            strokeWidth = 1f,
            start = Offset(0f, 0f),
            end = Offset(size.width - 0, 0f),
            pathEffect = pathEffect
        )
    }
}

