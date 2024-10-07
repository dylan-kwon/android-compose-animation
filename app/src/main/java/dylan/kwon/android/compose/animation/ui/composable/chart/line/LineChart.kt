package dylan.kwon.android.compose.animation.ui.composable.chart.line

import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dylan.kwon.android.compose.animation.ui.theme.ComposeanimationTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    data: ImmutableList<LineChartData>,
    dotSize: Dp = 8.dp,
    strokeWidth: Dp = 2.dp,
    animationDuration: Int = 1000
) {
    var canvasSize by remember {
        mutableStateOf(Size.Unspecified)
    }
    val maxValue = remember(data) {
        data.flatMap { it.values }.maxByOrNull { it } ?: 0f
    }
    val minValue = remember(data) {
        data.flatMap { it.values }.minByOrNull { it } ?: 0f
    }
    var dataState by remember(data) {
        mutableStateOf(
            data.map {
                it.copy(
                    values = it.values.map { 0f }.toImmutableList()
                )
            }
        )
    }
    LaunchedEffect(data) {
        dataState = data
    }
    val animatedData = with(LocalDensity.current) {
        dataState.map {
            it.values.mapIndexed { index, value ->
                animateOffsetAsState(
                    targetValue = calcOffset(
                        canvasSize = canvasSize,
                        dotSize = dotSize,
                        dataSize = it.values.size,
                        index = index,
                        value = value,
                        maxValue = maxValue,
                        minValue = minValue
                    ),
                    animationSpec = tween(
                        durationMillis = animationDuration
                    ),
                    label = "offset-animation"
                )
            }
        }
    }
    Canvas(modifier) {
        if (canvasSize != size) {
            canvasSize = size
        }
        if (canvasSize == Size.Unspecified) {
            return@Canvas
        }
        animatedData.forEachIndexed { i, offsets ->
            val color = data[i].color
            offsets.forEachIndexed { j, offset ->
                val nextOffset = offsets.getOrNull(j + 1)
                if (nextOffset != null) drawLine(
                    color = color,
                    start = offset.value,
                    end = nextOffset.value,
                    strokeWidth = strokeWidth.toPx()
                )
                drawCircle(
                    color = color,
                    center = offset.value,
                    radius = dotSize.div(2).toPx()
                )
            }
        }
    }
}

private fun Density.calcOffset(
    canvasSize: Size,
    dotSize: Dp,
    dataSize: Int,
    index: Int,
    value: Float,
    maxValue: Float,
    minValue: Float
): Offset {
    return Offset(
        x = calcXOffset(
            canvasWidth = canvasSize.width,
            dotSize = dotSize,
            dataSize = dataSize,
            index = index
        ),
        y = calcYOffset(
            canvasHeight = canvasSize.height,
            dotSize = dotSize,
            value = value,
            maxValue = maxValue,
            minValue = minValue
        )
    )
}

private fun Density.calcXOffset(
    canvasWidth: Float,
    dotSize: Dp,
    dataSize: Int,
    index: Int
): Float {
    val drawableWidth = canvasWidth - dotSize.toPx()
    val dotHalfSize = dotSize.div(2)
    return (drawableWidth / (dataSize - 1) * index) + dotHalfSize.toPx()
}

private fun Density.calcYOffset(
    canvasHeight: Float,
    dotSize: Dp,
    value: Float,
    maxValue: Float,
    minValue: Float
): Float {
    val relativeValue = value - minValue
    val relativeMaxValue = maxValue - minValue
    val drawableHeight = canvasHeight - dotSize.toPx()
    val dotHalfSize = dotSize.div(2)
    return drawableHeight - (drawableHeight * relativeValue / relativeMaxValue) + dotHalfSize.toPx()
}

@Composable
@Preview(showBackground = true)
private fun LineChartPreview() {
    ComposeanimationTheme {
        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            data = persistentListOf(
                LineChartData(
                    color = MaterialTheme.colorScheme.primary,
                    values = persistentListOf(100f, 150f, 180f, 200f, 170f)
                ),
                LineChartData(
                    color = MaterialTheme.colorScheme.scrim,
                    values = persistentListOf(10f, 30f, 270f, 150f, 40f)
                ),
                LineChartData(
                    color = MaterialTheme.colorScheme.error,
                    values = persistentListOf(210f, 80f, 130f, 20f, 90f)
                ),
            )
        )
    }
}