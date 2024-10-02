package dylan.kwon.android.compose.animation.ui.composable.cardflip

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dylan.kwon.android.compose.animation.ui.theme.ComposeanimationTheme


@Composable
fun CardFlip(
    modifier: Modifier,
    state: CardFlipState = rememberCardFlipState(),
    flipDuration: Int = 1_000
) {
    val color by animateColorAsState(
        targetValue = when (state.isFlipped) {
            true -> MaterialTheme.colorScheme.primaryContainer
            else -> MaterialTheme.colorScheme.tertiaryContainer
        },
        animationSpec = tween(
            durationMillis = flipDuration,
            easing = EaseInOut
        ),
        label = "color-state"
    )

    val rotateY by animateFloatAsState(
        targetValue = when (state.isFlipped) {
            true -> 180f
            else -> 0f
        },
        animationSpec = tween(
            durationMillis = flipDuration,
            easing = EaseInOut
        ),
        label = "rotate-card-y"
    )

    Box(
        modifier = modifier then Modifier
            .graphicsLayer {
                rotationY = rotateY
                cameraDistance = 20f
            }
            .clip(RoundedCornerShape(24.dp))
            .clickable(onClick = state::flip)
            .background(color)
    )
}

@Preview
@Composable
private fun CardFlipPreview() {
    ComposeanimationTheme {
        CardFlip(
            modifier = Modifier.size(200.dp, 300.dp)
        )
    }
}