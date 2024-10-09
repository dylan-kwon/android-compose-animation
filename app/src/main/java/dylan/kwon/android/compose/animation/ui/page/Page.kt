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
    CardFlip(
        nameResId = R.string.card_flip
    ),
    BarChart(
        nameResId = R.string.bar_chart
    ),
    Counter(
        nameResId = R.string.counter
    ),
    PieChart(
        nameResId = R.string.pie_chart
    ),
    CircularProgressBar(
        nameResId = R.string.circular_progress_bar
    ),
    LineChart(
        nameResId = R.string.line_chart
    ),
    StackedChart(
        nameResId = R.string.stacked_chart
    ),
    Timer(
        nameResId = R.string.timer
    ),
}