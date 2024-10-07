package dylan.kwon.android.compose.animation.ui.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import dylan.kwon.android.compose.animation.ui.composable.chart.stacked.StackedChart
import dylan.kwon.android.compose.animation.ui.composable.chart.stacked.StackedChartData
import dylan.kwon.android.compose.animation.ui.theme.ComposeanimationTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.random.Random

@Composable
fun StackedChartPage() {
    val colors = persistentListOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.scrim,
        MaterialTheme.colorScheme.error,
    )
    var data by remember {
        mutableStateOf(generateRandomData(colors))
    }
    LaunchedEffect(Unit) {
        while (isActive) {
            delay(1500)
            data = generateRandomData(colors)
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        StackedChart(
            modifier = Modifier
                .padding(horizontal = 80.dp)
                .fillMaxWidth()
                .aspectRatio(6 / 1f),
            data = data
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun StackedChartPagePreview() {
    ComposeanimationTheme {
        StackedChartPage()
    }
}

private fun generateRandomData(
    colors: ImmutableList<Color>
): ImmutableList<StackedChartData> {

    val a = Random.nextDouble(0.0, 0.4999)
    val b = Random.nextDouble(0.0, 0.4999)
    val c = 1 - (a + b)

    return persistentListOf(
        StackedChartData(
            color = colors[0],
            fraction = a.toFloat()
        ),
        StackedChartData(
            color = colors[1],
            fraction = b.toFloat()
        ),
        StackedChartData(
            color = colors[2],
            fraction = c.toFloat()
        )
    )
}