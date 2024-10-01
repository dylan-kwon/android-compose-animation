package dylan.kwon.android.compose.animation.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dylan.kwon.android.compose.animation.R
import dylan.kwon.android.compose.animation.ui.composable.counter.Counter
import dylan.kwon.android.compose.animation.ui.theme.ComposeanimationTheme
import kotlin.random.Random

@Composable
fun CounterPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        var value by rememberSaveable {
            mutableIntStateOf(0)
        }
        Counter(
            targetValue = value
        )
        ElevatedButton(
            onClick = {
                value = getRandomValue()
            }
        ) {
            Text(stringResource(R.string.random))
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun CounterPagePreview() {
    ComposeanimationTheme {
        CounterPage()
    }
}

private fun getRandomValue(): Int {
    return Random.nextInt(0, Int.MAX_VALUE)
}