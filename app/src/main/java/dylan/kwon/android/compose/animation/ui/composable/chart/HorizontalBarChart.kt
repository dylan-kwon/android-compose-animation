package dylan.kwon.android.compose.animation.ui.composable.chart

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dylan.kwon.android.compose.animation.ui.theme.ComposeanimationTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun HorizontalBarChart(
    modifier: Modifier = Modifier,
    data: ImmutableList<Float>,
    durationMillis: Int = 1_000
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start,
    ) {
        data.forEach { value ->
            Bar(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                durationMillis = durationMillis
            )
        }
    }
}

@Composable
private fun Bar(
    modifier: Modifier = Modifier,
    value: Float,
    durationMillis: Int,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        var valueState by remember {
            mutableFloatStateOf(0f)
        }
        val valueAnimation by animateFloatAsState(
            targetValue = valueState,
            animationSpec = tween(
                durationMillis = durationMillis,
                easing = EaseInOut
            ),
            label = "value-animation",
        )
        LaunchedEffect(value) {
            valueState = value
        }
        BoxWithConstraints(
            modifier = Modifier.weight(1f, fill = false)
        ) {
            Box(
                modifier = Modifier
                    .width(maxWidth.times(valueAnimation))
                    .height(40.dp)
                    .clip(RoundedCornerShape(topEndPercent = 25, bottomEndPercent = 25))
                    .background(MaterialTheme.colorScheme.primaryContainer)
            )
        }
        Text(
            modifier = Modifier
                .padding(horizontal = 8.dp),
            text = "%.2f".format(valueAnimation)
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun HorizontalBarChartPreview() {
    ComposeanimationTheme {
        HorizontalBarChart(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            data = persistentListOf(
                0.2f, 0.4f, 0.6f, 0.8f, 1.0f
            )
        )
    }
}