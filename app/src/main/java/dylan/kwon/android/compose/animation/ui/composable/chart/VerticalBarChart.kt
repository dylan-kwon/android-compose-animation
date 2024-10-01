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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
fun VerticalBarChart(
    modifier: Modifier = Modifier,
    data: ImmutableList<Float>,
    durationMillis: Int = 1_000
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        data.forEach { value ->
            Bar(
                modifier = Modifier.fillMaxHeight(),
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
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
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
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = "%.2f".format(valueAnimation)
        )
        BoxWithConstraints(
            modifier = Modifier.weight(1f, fill = false)
        ) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(maxHeight.times(valueAnimation))
                    .clip(RoundedCornerShape(topStartPercent = 25, topEndPercent = 25))
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun VerticalBarChartPreview() {
    ComposeanimationTheme {
        VerticalBarChart(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            data = persistentListOf(
                0.2f, 0.4f, 0.6f, 0.8f, 1.0f
            )
        )
    }
}