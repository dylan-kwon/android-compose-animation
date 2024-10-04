package dylan.kwon.android.compose.animation.ui.composable.chart.line

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dylan.kwon.android.compose.animation.ui.theme.ComposeanimationTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    data: ImmutableList<LineChartData>,
    dotSize: Dp = 8.dp,
    strokeWidth: Dp = 2.dp,
    animationDuration: Int = 1000
) {
    val maxValue = remember(data) {
        data.flatMap { it.values }.maxByOrNull { it } ?: 0f
    }
    val minValue = remember(data) {
        data.flatMap { it.values }.minByOrNull { it } ?: 0f
    }

    Canvas(modifier) {
        data.forEach { lineCharData ->

            val offsetList = lineCharData.values.mapIndexed { i, e ->
                val xOffset = calcXOffset(
                    canvasWidth = size.width,
                    dotSize = dotSize,
                    dataSize = lineCharData.values.size,
                    index = i
                )
                val yOffset = calcYOffset(
                    canvasHeight = size.height,
                    dotSize = dotSize,
                    value = e,
                    maxValue = maxValue,
                    minValue = minValue
                )
                Offset(xOffset, yOffset)
            }

            offsetList.forEachIndexed { index, offset ->
                val nextOffset = offsetList.getOrNull(index + 1)
                if (nextOffset != null) drawLine(
                    color = lineCharData.color,
                    start = offset,
                    end = nextOffset,
                    strokeWidth = strokeWidth.toPx()
                )
                drawCircle(
                    color = lineCharData.color,
                    center = offset,
                    radius = dotSize.div(2).toPx()
                )
            }
        }
    }
}

private fun DrawScope.calcXOffset(
    canvasWidth: Float,
    dotSize: Dp,
    dataSize: Int,
    index: Int
): Float {
    val drawableWidth = canvasWidth - dotSize.toPx()
    val dotHalfSize = dotSize.div(2)
    return (drawableWidth / (dataSize - 1) * index) + dotHalfSize.toPx()
}

private fun DrawScope.calcYOffset(
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