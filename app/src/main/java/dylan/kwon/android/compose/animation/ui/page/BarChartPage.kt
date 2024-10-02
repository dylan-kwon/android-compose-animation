package dylan.kwon.android.compose.animation.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dylan.kwon.android.compose.animation.ui.composable.chart.bar.HorizontalBarChart
import dylan.kwon.android.compose.animation.ui.composable.chart.bar.VerticalBarChart
import dylan.kwon.android.compose.animation.ui.theme.ComposeanimationTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.random.Random

@Composable
fun BarChartPage() {

    var data by remember {
        mutableStateOf(generateRandomData())
    }
    LaunchedEffect(Unit) {
        while (isActive) {
            delay(1500)
            data = generateRandomData()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HorizontalBarChart(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            data = data,
        )

        VerticalBarChart(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            data = data,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun BarChartPagePreview() {
    ComposeanimationTheme {
        BarChartPage()
    }
}

private fun generateRandomData(): ImmutableList<Float> {
    return mutableListOf<Float>().apply {
        repeat(5) {
            this += Random.nextFloat()
        }
    }.toImmutableList()
}