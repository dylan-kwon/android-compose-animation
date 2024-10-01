@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import dylan.kwon.android.compose.animation.ui.page.BarChartPage
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
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(currentPage.nameResId))
                },
                navigationIcon = {
                    if (!isMain) BackButton {
                        currentPage = Page.Main
                    }
                }
            )
        }
    ) {
        AnimatedContent(
            modifier = Modifier.padding(it),
            targetState = currentPage,
            label = "content"
        ) { targetPage ->
            when (targetPage) {
                Page.Main -> MainList(
                    onClickFlipCard = { page ->
                        currentPage = page
                    }
                )

                Page.FlipCard -> {
                    FlipCardPage()
                }

                Page.BarChart -> {
                    BarChartPage()
                }
            }
        }
    }
    BackHandler(enabled = !isMain) {
        currentPage = Page.Main
    }
}

@Composable
private fun BackButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.go_to_back)
        )
    }
}

@Composable
private fun MainList(
    onClickFlipCard: (page: Page) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        val pages = remember {
            Page.entries.drop(1)
        }
        pages.forEach { page ->
            MoveButton(
                text = stringResource(page.nameResId),
                onClick = {
                    onClickFlipCard(page)
                }
            )
        }
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