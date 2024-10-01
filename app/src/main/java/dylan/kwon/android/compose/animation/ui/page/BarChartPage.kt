package dylan.kwon.android.compose.animation.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dylan.kwon.android.compose.animation.ui.composable.chart.VerticalBarChart
import dylan.kwon.android.compose.animation.ui.theme.ComposeanimationTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun BarChartPage(
    data: ImmutableList<Float> = persistentListOf(
        0.2f, 0.4f, 0.6f, 0.8f, 1.0f
    )
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
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
fun BarChartPagePreview() {
    ComposeanimationTheme {
        BarChartPage()
    }
}