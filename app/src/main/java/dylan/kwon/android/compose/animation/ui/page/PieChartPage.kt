package dylan.kwon.android.compose.animation.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dylan.kwon.android.compose.animation.ui.composable.chart.pie.PieChart
import dylan.kwon.android.compose.animation.ui.composable.chart.pie.PieChartData
import dylan.kwon.android.compose.animation.ui.theme.ComposeanimationTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun PieChartPage() {
    val colors = listOf(
        MaterialTheme.colorScheme.inversePrimary,
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer
    )
    var data by remember {
        mutableStateOf<ImmutableList<PieChartData>>(persistentListOf())
    }
    LaunchedEffect(Unit) {
        while (true) {
            data = generatorRandomData(colors)
            delay(1_500)
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(50.dp, Alignment.CenterVertically),
    ) {
        val chartModifier = Modifier
            .padding(horizontal = 80.dp)
            .aspectRatio(1f)

        PieChart(
            modifier = chartModifier,
            data = data
        )
        PieChart(
            modifier = chartModifier,
            data = data,
            gap = 10.dp,
            useCenter = false,
            style = Stroke(
                width = with(LocalDensity.current) {
                    10.dp.toPx()
                },
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PieChartPagePreview() {
    ComposeanimationTheme {
        PieChartPage()
    }
}

private fun generatorRandomData(colors: List<Color>): ImmutableList<PieChartData> {
    val numbers = mutableListOf<Double>().apply {
        this += Random.nextDouble(0.0, 0.4999)
        this += Random.nextDouble(0.0, 0.4999)
        this += 1 - (first() + last())
    }
    return numbers.mapIndexed { index, value ->
        PieChartData(
            fraction = value.toFloat(),
            color = colors[index]
        )
    }.toImmutableList()
}