package dylan.kwon.android.compose.animation.ui.composable.chart.pie

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    data: ImmutableList<PieChartData>,
    gap: Dp = 0.dp,
    useCenter: Boolean = true,
    style: DrawStyle = Fill,
    animationDuration: Int = 1000
) {
    val progressAnimatable = remember {
        Animatable(0f)
    }
    LaunchedEffect(data) {
        with(progressAnimatable) {
            snapTo(0f)
            animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = animationDuration,
                    easing = FastOutSlowInEasing
                )
            )
        }
    }
    Canvas(modifier = modifier) {

        var startAngle = 0f

        val circumference = (size.width * Math.PI).toFloat()
        val gapPx = gap.toPx()

        val gapFraction = gapPx * 360f / circumference

        data.forEach {
            val sweepAngle = 360f * it.fraction
            drawArc(
                color = it.color,
                startAngle = startAngle,
                sweepAngle = (sweepAngle - gapFraction) * progressAnimatable.value,
                useCenter = useCenter,
                size = size,
                style = style
            )
            startAngle += sweepAngle
        }
    }
}