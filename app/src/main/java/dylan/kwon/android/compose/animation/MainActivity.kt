package dylan.kwon.android.compose.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dylan.kwon.android.compose.animation.ui.page.FlipCardPage
import dylan.kwon.android.compose.animation.ui.page.Page
import dylan.kwon.android.compose.animation.ui.theme.ComposeanimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeanimationTheme {
                MainScreen()

            }
        }
    }
}


@Composable
fun MainScreen() {
    var currentPage by rememberSaveable {
        mutableStateOf(Page.Main)
    }
    val isMain by remember {
        derivedStateOf {
            currentPage == Page.Main
        }
    }
    Scaffold {
        AnimatedContent(
            modifier = Modifier.padding(it),
            targetState = currentPage,
            label = "content"
        ) { targetPage ->
            when (targetPage) {
                Page.Main -> MainList(
                    onClickFlipCard = {
                        currentPage = Page.FlipCard
                    }
                )

                Page.FlipCard -> {
                    FlipCardPage()
                }
            }
        }
    }
    BackHandler(enabled = !isMain) {
        currentPage = Page.Main
    }
}

@Composable
private fun MainList(
    onClickFlipCard: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        MoveButton(
            text = stringResource(R.string.flip_card),
            onClick = onClickFlipCard
        )
    }
}

@Composable
private fun MoveButton(
    text: String,
    onClick: () -> Unit
) {
    ElevatedButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Text(text)
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    ComposeanimationTheme {
        MainScreen()
    }
}