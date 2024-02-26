package com.mth.mycomposestarterkit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.mth.mycomposestarterkit.ui.theme.White
import com.mth.mycomposestarterkit.ui.theme.White75
import kotlin.math.absoluteValue


/**
 * @Author myothiha
 * Created 21/02/2024 at 11:33 AM.
 **/
@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarouselMovieSection() {
    val images = getImageList()
    val pagerState = com.google.accompanist.pager.rememberPagerState(initialPage = 0)
    Column {
        com.google.accompanist.pager.HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            count = images.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 28.dp)
        ) { currentPage ->
            CarouselMovieItem(currentPage = currentPage, images = images)

        }
        Text(
            text = "No Time No Die ${pagerState.currentPage}",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 12.dp),
            style = TextStyle(
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            ),
            fontSize = 22.sp,
            color = White
        )

        Text(
            text = "2h29m * Action, adventure * sci-fi",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
            fontSize = 16.sp,
            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
            color = White75
        )


        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Gray.copy(alpha = 0.2f)),
            activeColor = Color.Yellow,
            inactiveColor = Color.Transparent,
            indicatorWidth = 12.dp,
            indicatorHeight = 8.dp,
            spacing = 2.dp,
            indicatorShape = RoundedCornerShape(12.dp)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun PagerScope.CarouselMovieItem(currentPage: Int, images: List<String>) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.graphicsLayer {
            val pageOffset = calculateCurrentOffsetForPage(currentPage).absoluteValue
            lerp(start = 0.90f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)).also {
                scaleX = it
                scaleY = it
            }
            alpha = lerp(start = 0.3f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f))
        }
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            model = images[currentPage],
            contentDescription = "Movie Image"
        )
    }
}

fun getImageList(): List<String> {
    return listOf<String>(
        "https://image.tmdb.org/t/p/original/eqWaeh21e4ZgHjwpULZVHCGIq9X.jpg",
        "https://image.tmdb.org/t/p/original/1BIoJGKbXjdFDAqUEiA2VHqkK1Z.jpg",
        "https://image.tmdb.org/t/p/original/eqWaeh21e4ZgHjwpULZVHCGIq9X.jpg",
        "https://image.tmdb.org/t/p/original/1BIoJGKbXjdFDAqUEiA2VHqkK1Z.jpg",
        "https://image.tmdb.org/t/p/original/eqWaeh21e4ZgHjwpULZVHCGIq9X.jpg",
        "https://image.tmdb.org/t/p/original/1BIoJGKbXjdFDAqUEiA2VHqkK1Z.jpg",
    )
}
