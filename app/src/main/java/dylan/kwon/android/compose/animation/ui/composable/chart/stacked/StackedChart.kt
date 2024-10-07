package dylan.kwon.android.compose.animation.ui.composable.chart.stacked

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dylan.kwon.android.compose.animation.ui.theme.ComposeanimationTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf


@Composable
fun StackedChart(
    modifier: Modifier = Modifier,
    data: ImmutableList<StackedChartData>,
    animationDuration: Int = 1_000
) {
    var dataState by remember(data) {
        mutableStateOf(
            data.map {
                it.copy(fraction = 0.1f)
            }
        )
    }
    val animatedData = dataState.map {
        animateFloatAsState(
            targetValue = it.fraction,
            animationSpec = tween(animationDuration),
            label = "fraction-animation"
        )
    }
    LaunchedEffect(data) {
        dataState = data
    }
    Row(
        modifier = Modifier
            .width(intrinsicSize = IntrinsicSize.Min)
            .height(intrinsicSize = IntrinsicSize.Min)
            .then(modifier)
    ) {
        dataState.forEachIndexed { index, e ->
            Stack(
                color = e.color,
                fraction = animatedData[index].value,
                isStartRound = index == 0,
                isEndRound = index == data.size - 1
            )
        }
    }
}

@Composable
private fun RowScope.Stack(
    color: Color,
    fraction: Float,
    isStartRound: Boolean,
    isEndRound: Boolean,
) {
    Box(
        modifier = Modifier
            .run {
                when {
                    isStartRound -> clip(
                        RoundedCornerShape(
                            topStartPercent = 15,
                            bottomStartPercent = 15
                        )
                    )

                    isEndRound -> clip(
                        RoundedCornerShape(
                            topEndPercent = 15,
                            bottomEndPercent = 15
                        )
                    )

                    else -> this
                }
            }
            .fillMaxHeight()
            .background(color = color)
            .weight(fraction)
    )
}

@Composable
@Preview(showBackground = true)
fun StackedChartPreview() {
    ComposeanimationTheme {
        StackedChart(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(10 / 1f),
            data = persistentListOf(
                StackedChartData(color = MaterialTheme.colorScheme.primary, fraction = 0.5f),
                StackedChartData(color = MaterialTheme.colorScheme.secondary, fraction = 0.3f),
                StackedChartData(color = MaterialTheme.colorScheme.tertiary, fraction = 0.2f),
            )
        )
    }
}