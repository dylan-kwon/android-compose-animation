package dylan.kwon.android.compose.animation.ui.composable.counter

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun Counter(
    targetValue: Int,
    initialValue: Int = 0,
    animateDuration: Int = 500
) {
    var valueState by rememberSaveable {
        mutableIntStateOf(initialValue)
    }
    val valueAnimation by animateIntAsState(
        targetValue = valueState,
        animationSpec = tween(animateDuration),
        label = "value-animation"
    )
    LaunchedEffect(targetValue) {
        valueState = targetValue
    }

    val formatValue = String.format(
        LocalConfiguration.current.locales.get(0),
        "%,d",
        valueAnimation
    )
    Text(
        text = formatValue,
        style = MaterialTheme.typography.displayMedium
    )
}