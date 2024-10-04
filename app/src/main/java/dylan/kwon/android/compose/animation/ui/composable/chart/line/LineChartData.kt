package dylan.kwon.android.compose.animation.ui.composable.chart.line

import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.ImmutableList

data class LineChartData(
    val color: Color,
    val values: ImmutableList<Float>,
)