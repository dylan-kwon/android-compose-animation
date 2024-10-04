package dylan.kwon.android.compose.animation.ui.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dylan.kwon.android.compose.animation.ui.composable.chart.line.LineChart
import dylan.kwon.android.compose.animation.ui.composable.chart.line.LineChartData
import dylan.kwon.android.compose.animation.ui.theme.ComposeanimationTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun LineChartPage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val colors = persistentListOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.scrim,
            MaterialTheme.colorScheme.error,
        )
        var data by remember {
            mutableStateOf<ImmutableList<LineChartData>>(
                persistentListOf()
            )
        }
        LaunchedEffect(Unit) {
            while (true) {
                data = generateRandomData(colors)
                delay(1_500)
            }
        }
        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            data = data
        )
    }
}

private fun generateRandomData(colors: ImmutableList<Color>): ImmutableList<LineChartData> {
    return mutableListOf<LineChartData>().apply {
        repeat(3) {
            this += LineChartData(
                color = colors[it],
                values = mutableListOf<Float>().apply {
                    repeat(5) {
                        this += Random.nextDouble(0.0, 300.0).toFloat()
                    }
                }.toImmutableList()
            )
        }
    }.toImmutableList()
}

@Composable
@Preview(showBackground = true)
fun LineChartPagePreview() {
    ComposeanimationTheme {
        LineChartPage()
    }
}