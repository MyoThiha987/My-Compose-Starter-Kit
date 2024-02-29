package com.mth.mycomposestarterkit

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.mth.mycomposestarterkit.data.MovieVO
import com.mth.mycomposestarterkit.data.getMovieList
import com.mth.mycomposestarterkit.ui.theme.Black
import com.mth.mycomposestarterkit.ui.theme.White
import com.mth.mycomposestarterkit.ui.theme.Yellow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

/**
 * @Author myothiha
 * Created 27/02/2024 at 10:33 PM.
 **/

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MoviesScreen(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState()
    val tabList = mutableListOf("Now Playing", "Coming Soon")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {

        TabRowView(tabList = tabList, pagerState = pagerState)
        HorizontalPager(
            count = tabList.size,
            state = pagerState
        ) {
            when (it) {
                0 -> MoviesScreen(showAll = true)
                1 -> MoviesScreen(showAll = false)
            }
        }
    }
}

@Composable
fun MoviesScreen(showAll: Boolean) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        state = rememberLazyGridState(),
        contentPadding = PaddingValues(16.dp),
        columns = GridCells.Fixed(count = 2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
       val data = if (showAll) getMovieList() else getMovieList().subList(0, 2)
        items(items = data) { data: MovieVO ->
            ComingSoonMovieView(data =data, navController = rememberNavController())
        }

    }
}



@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabRowView(
    tabList: List<String>,
    pagerState: PagerState
) {
    TabRow(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = Black, shape = RoundedCornerShape(10.dp))
            .padding(3.dp),
        selectedTabIndex = pagerState.currentPage,
        containerColor = Black,
        contentColor = Color.White,
        indicator = {
            HomeTabIndicator(
                tabPositions = it,
                tabPage = pagerState.currentPage,
                pagerState = pagerState
            )
        },
        divider = {}
    ) {
        tabList.forEachIndexed { index, text ->
            TabItem(
                index = index,
                pagerState = pagerState,
                text = text
            )
        }

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HomeTabIndicator(
    tabPositions: List<TabPosition>,
    tabPage: Int,
    pagerState: PagerState
) {
    val transition = updateTransition(
        tabPage,
        label = "Tab indicator"
    )
    val indicatorLeft by transition.animateDp(
        transitionSpec = {
            if (pagerState.currentPage isTransitioningTo pagerState.targetPage) {
                // Indicator moves to the right.
                // Low stiffness spring for the left edge so it moves slower than the right edge.
                spring(stiffness = Spring.StiffnessMedium)
            } else {
                // Indicator moves to the left.
                // Medium stiffness spring for the left edge so it moves faster than the right edge.
                spring(stiffness = Spring.StiffnessLow)
            }
        },
        label = "Indicator left"
    ) { page ->
        tabPositions[page].left
    }

    val indicatorRight by transition.animateDp(
        transitionSpec = {
            if (pagerState.currentPage isTransitioningTo pagerState.targetPage) {
                // Indicator moves to the right
                // Medium stiffness spring for the right edge so it moves faster than the left edge.
                spring(stiffness = Spring.StiffnessLow)
            } else {
                // Indicator moves to the left.
                // Low stiffness spring for the right edge so it moves slower than the left edge.
                spring(stiffness = Spring.StiffnessMedium)
            }
        },
        label = "Indicator right"
    ) { page ->
        tabPositions[page].right
    }

    val color by transition.animateColor(
        label = "Border color"
    ) { page ->
        if (page == pagerState.currentPage) Yellow else Black
    }
    Box(
        Modifier
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = indicatorLeft)
            .width(indicatorRight - indicatorLeft)
            .padding(1.dp)
            .fillMaxSize()
            .background(color = color, shape = RoundedCornerShape(10.dp))
            .zIndex(1f)
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabItem(
    index: Int,
    pagerState: PagerState,
    text: String
) {
    val coroutineScope = rememberCoroutineScope()
    val selected = pagerState.currentPage == index


    Tab(
        modifier = Modifier.zIndex(6f),
        text = {
            Text(
                text = text,
                color = if (selected) Color.White else White
            )
        },
        interactionSource = DisabledInteractionSource(),
        selected = pagerState.currentPage == index,
        onClick = {
            coroutineScope.launch {
                pagerState.animateScrollToPage(index)
            }
        }
    )
}

@Composable
@Preview
fun MoviesScreenPreview() {
    MoviesScreen()
}

class DisabledInteractionSource : MutableInteractionSource {
    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}