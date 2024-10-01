package dylan.kwon.android.compose.animation.ui.page

import androidx.annotation.StringRes
import dylan.kwon.android.compose.animation.R

enum class Page(
    @StringRes
    val nameResId: Int
) {
    Main(
        nameResId = R.string.main
    ),
    FlipCard(
        nameResId = R.string.flip_card
    ),
    BarChart(
        nameResId = R.string.bar_chart
    ),
    Counter(
        nameResId = R.string.counter
    )
}