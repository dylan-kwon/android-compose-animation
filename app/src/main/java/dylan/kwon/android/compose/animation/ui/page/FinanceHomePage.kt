package dylan.kwon.android.compose.animation.ui.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dylan.kwon.android.compose.animation.ui.theme.ComposeanimationTheme

@Composable
fun FinanceHomePage() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

    }
}

@Composable
@Preview(showBackground = true)
fun FinanceHomePagePreview() {
    ComposeanimationTheme {
        FinanceHomePage()
    }
}