package dylan.kwon.android.compose.animation.ui.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dylan.kwon.android.compose.animation.ui.composable.timer.Timer
import dylan.kwon.android.compose.animation.ui.theme.ComposeanimationTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.random.Random

@Composable
fun TimerPage() {

    var seconds by remember {
        mutableStateOf(0)
    }
    LaunchedEffect(Unit) {
        while (isActive) {
            val random = getRandomSeconds()
            seconds = random
            delay(random * 1000L)

        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Timer(
            modifier = Modifier
                .padding(54.dp)
                .fillMaxWidth()
                .aspectRatio(1f),
            seconds = seconds
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun TimerPagePreview() {
    ComposeanimationTheme {
        TimerPage()
    }
}

private fun getRandomSeconds(): Int {
    return Random.nextInt(0, 60)
}