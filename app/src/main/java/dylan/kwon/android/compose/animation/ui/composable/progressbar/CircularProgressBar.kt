package dylan.kwon.android.compose.animation.ui.composable.progressbar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dylan.kwon.android.compose.animation.ui.theme.ComposeanimationTheme

object CircularProgressBar {
    data class Colors(
        val progress: Color? = null,
        val background: Color? = null
    )
}

@Composable
fun CircularProgressbar(
    modifier: Modifier = Modifier,
    value: Float,
    thickness: Dp = 16.dp,
    colors: CircularProgressBar.Colors = CircularProgressBar.Colors()
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        var valueState by remember(value) {
            mutableFloatStateOf(0f)
        }
        val valueAnimation by animateFloatAsState(
            targetValue = valueState,
            animationSpec = tween(durationMillis = 1_000),
            label = "progress_animation"
        )
        LaunchedEffect(value) {
            valueState = value
        }
        val progressColor = colors.progress ?: MaterialTheme.colorScheme.primaryContainer
        val backgroundColor = colors.background ?: MaterialTheme.colorScheme.outline

        Canvas(modifier = Modifier.fillMaxSize()) {
            val thicknessPx = thickness.toPx()

            drawArc(
                color = backgroundColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = thicknessPx)
            )
            drawArc(
                color = progressColor,
                startAngle = 270f,
                sweepAngle = 360 * valueAnimation,
                useCenter = false,
                style = Stroke(
                    width = thicknessPx,
                    cap = StrokeCap.Round,

                    )
            )
        }

        val formatValue = remember(valueAnimation) {
            "${(valueAnimation * 100).toInt()}%"
        }
        Text(
            text = formatValue,
            style = MaterialTheme.typography.displayMedium
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun CircularProgressbarPreview() {
    ComposeanimationTheme {
        CircularProgressbar(
            modifier = Modifier.aspectRatio(1f),
            value = 1.0f
        )
    }
}