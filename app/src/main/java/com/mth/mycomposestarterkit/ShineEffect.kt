package com.mth.mycomposestarterkit

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * @Author myothiha
 * Created 08/02/2024 at 10:23 PM.
 **/

fun Modifier.shineEffect(
    colorList: List<Color> = listOf(
        Color.Yellow.copy(alpha = 0.3f),
        Color.Cyan.copy(alpha = 0.5f),
        Color.Cyan.copy(alpha = 1f),
        Color.Cyan.copy(alpha = 0.5f),
        Color.Cyan.copy(alpha = 0.3f)
        /*Color.White.copy(alpha = 0.3f),
        Color.White.copy(alpha = 0.5f),
        Color.White.copy(alpha = 1.0f),
        Color.White.copy(alpha = 0.5f),
        Color.White.copy(alpha = 0.3f),*/
    ),
    widthOfShadowBrush: Int = 500,
    angleOfAxisY: Float = -180f,
    durationMillis: Int = 1000,
): Modifier {
    return composed {
        val transition = rememberInfiniteTransition(label = "")
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = (widthOfShadowBrush + durationMillis).toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = durationMillis,
                    easing = LinearEasing

                ),
                repeatMode = RepeatMode.Restart
            ),
            label = "infinite animation"
        )

        Log.d("Animation","Total : ${translateAnimation.value} ")
        this.background(
            brush = Brush.linearGradient(
                colors = colorList,
                start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0f),
                end = Offset(x = translateAnimation.value, y = angleOfAxisY)

            )
        )
    }

}