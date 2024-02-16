package com.mth.mycomposestarterkit

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @Author myothiha
 * Created 06/02/2024 at 10:52 PM.
 **/


@Composable
fun AnimatedBorder(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    borderWidth: Dp = 2.dp,
    gradientColor: Brush = Brush.sweepGradient(listOf(Color.Gray, Color.White)),
    animationDuration: Int = 1000,
    content: @Composable () -> Unit

) {
    val infiniteTransition = rememberInfiniteTransition("Infinite border animation")
    val degree by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "infinit"
    )

    Surface(
        modifier = modifier.clickable { },
        shape = shape
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(borderWidth)
                .drawWithContent {
                    rotate(degrees = degree) {
                        drawCircle(
                            brush = gradientColor,
                            radius = size.width,
                            blendMode = BlendMode.SrcIn
                        )
                    }
                    drawContent()
                },
            shape = shape,
            color = MaterialTheme.colorScheme.surface
        ) {
            content()
        }
    }

}

@Preview(name = "AnimateBorderPreview")
@Composable
fun AnimatedBorderPreview() {
    AnimatedBorder {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            text = "jfkdfjkdjfkdjfkdjfkdjfkdjfkdjfkjdkjfkdjfkdjfkdjfkjdfkjdfkjd"
        )
    }

}