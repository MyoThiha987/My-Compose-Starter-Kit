package com.mth.mycomposestarterkit.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * @Author myothiha
 * Created 22/02/2024 at 11:02 AM.
 **/

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> HorizontalTitleSection(
    modifier: Modifier = Modifier,
    list: List<T>,
    usedFlingBehavior: Boolean,
    state: LazyListState = rememberLazyListState(),
    content: @Composable (T) -> Unit
) {
    LazyRow(
        state = state,
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 16.dp),
        flingBehavior = if (usedFlingBehavior) rememberSnapFlingBehavior(lazyListState = state) else ScrollableDefaults.flingBehavior()
    ) {
        items(items = list) { data ->
            content(data)
        }
    }

}