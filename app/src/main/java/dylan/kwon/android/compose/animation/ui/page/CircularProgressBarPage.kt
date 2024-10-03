package dylan.kwon.android.compose.animation.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dylan.kwon.android.compose.animation.ui.composable.progressbar.CircularProgressbar
import dylan.kwon.android.compose.animation.ui.theme.ComposeanimationTheme
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun CircularProgressBarPage() {

    var value by remember {
        mutableFloatStateOf(0f)
    }
    LaunchedEffect(Unit) {
        value = 1f
        while (true) {
            delay(1_500)
            value = generateRandomValue()
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressbar(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(50.dp),
            value = value
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun CircularProgressBarPagePreview() {
    ComposeanimationTheme {
        CircularProgressBarPage()
    }
}

private fun generateRandomValue(): Float {
    return Random.nextDouble(0.0, 1.0).toFloat()
}