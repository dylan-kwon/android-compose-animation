package dylan.kwon.android.compose.animation.ui.composable.cardflip

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun rememberCardFlipState(
    isFlipped: Boolean = false
) = rememberSaveable(saver = CardFlipState.Saver) {
    CardFlipState(
        isFlipped = isFlipped,
    )
}

@Stable
class CardFlipState(
    isFlipped: Boolean,
) {

    var isFlipped by mutableStateOf(isFlipped)
        private set

    fun flip() {
        isFlipped = !isFlipped
    }

    companion object {
        val Saver = listSaver(save = {
            listOf(
                it.isFlipped
            )
        }, restore = {
            CardFlipState(
                it[0] as Boolean,
            )
        })
    }
}

