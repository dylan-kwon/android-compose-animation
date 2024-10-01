package dylan.kwon.android.compose.animation.ui.composable.chart

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
            var isFirstComposition by rememberSaveable {
                mutableStateOf(true)
            }
            if (isFirstComposition) LaunchedEffect(data) {
                isFirstComposition = false
            }
            BoxWithConstraints {
                val height by animateDpAsState(
                    targetValue = when (isFirstComposition) {
                        true -> 0.dp
                        else -> maxHeight.times(value)
                    },
                    animationSpec = tween(
                        durationMillis = durationMillis,
                        easing = EaseInOut
                    ),
                    label = "height-animation"
                )
                Bar(
                    modifier = Modifier
                        .height(height)
                        .width(40.dp),
                    label = value.toString()
                )
            }
        }
    }
}

@Composable
private fun Bar(
    modifier: Modifier = Modifier,
    label: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(label)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .clip(RoundedCornerShape(topStartPercent = 25, topEndPercent = 25))
                .background(MaterialTheme.colorScheme.primary)
        )
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