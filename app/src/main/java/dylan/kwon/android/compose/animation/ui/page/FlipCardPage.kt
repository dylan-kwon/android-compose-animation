package dylan.kwon.android.compose.animation.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dylan.kwon.android.compose.animation.ui.composable.flipcard.FlipCard
import dylan.kwon.android.compose.animation.ui.theme.ComposeanimationTheme

@Composable
fun FlipCardPage() {
    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FlipCard(
                modifier = Modifier
                    .padding(horizontal = 50.dp)
                    .fillMaxWidth()
                    .aspectRatio(3 / 4f)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FlipCardPagePreview() {
    ComposeanimationTheme {
        FlipCardPage()
    }
}