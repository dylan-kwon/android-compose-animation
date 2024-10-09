package dylan.kwon.android.compose.animation.ui.composable.timer

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Timer(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(1f),

    seconds: Int
) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Background()
        CountDown(seconds = seconds)
    }
}

@Composable
private fun Background() {
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val radius = size.maxDimension / 2

        repeat(60) { index ->
            val angle = Math.toRadians(6.0 * index).toFloat()

            val isBold = index % 5 == 0
            val strokeWidth = when (isBold) {
                true -> 3.dp.toPx()
                else -> 1.dp.toPx()
            }
            val innerRadius = when (isBold) {
                true -> radius - 20.dp.toPx()
                else -> radius - 10.dp.toPx()
            }
            val startOffset = Offset(
                center.x + (radius * cos(angle)),
                center.y + (radius * sin(angle)),
            )
            val endOffset = Offset(
                center.x + (innerRadius * cos(angle)),
                center.y + (innerRadius * sin(angle)),
            )
            drawLine(
                color = Color.Black,
                start = startOffset,
                end = endOffset,
                strokeWidth = strokeWidth
            )
        }
    }
}

@Composable
private fun CountDown(
    color: Color = MaterialTheme.colorScheme.primaryContainer,
    seconds: Int
) {
    val animatedSeconds = remember(seconds) {
        Animatable(
            initialValue = seconds.toFloat() * 6,
        )
    }
    LaunchedEffect(seconds) {
        animatedSeconds.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = seconds * 1000,
                easing = LinearEasing
            ),
        )
    }
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        drawArc(
            color = color,
            startAngle = 270f,
            sweepAngle = animatedSeconds.value,
            useCenter = true,
            alpha = 0.5f,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun TimerPreView() {
    MaterialTheme {
        Timer(seconds = 15)
    }
}