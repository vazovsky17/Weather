package app.vazovsky.weather.extensions

import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import app.vazovsky.weather.R
import app.vazovsky.weather.presentation.views.recyclerview.LinearSpaceItemDecoration

/** Добавление декоратора для RecyclerView c LinearLayoutManager */
fun RecyclerView.addLinearSpaceItemDecoration(
    @DimenRes spacing: Int = R.dimen.padding_4,
    showFirstVerticalDivider: Boolean = false,
    showLastVerticalDivider: Boolean = false,
    showFirstHorizontalDivider: Boolean = false,
    showLastHorizontalDivider: Boolean = false,
    conditionProvider: LinearSpaceItemDecoration.ConditionProvider? = null,
) {
    this.addItemDecoration(
        LinearSpaceItemDecoration(
            context.resources.getDimensionPixelSize(spacing),
            showFirstVerticalDivider,
            showLastVerticalDivider,
            showFirstHorizontalDivider,
            showLastHorizontalDivider,
            conditionProvider,
        )
    )
}