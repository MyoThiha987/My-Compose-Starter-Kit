package com.mth.mycomposestarterkit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mth.mycomposestarterkit.components.HorizontalTitleSection
import com.mth.mycomposestarterkit.components.IconTextView
import com.mth.mycomposestarterkit.components.LabelAndContentSection
import com.mth.mycomposestarterkit.components.MovieImageView
import com.mth.mycomposestarterkit.data.MovieVO
import com.mth.mycomposestarterkit.data.ServiceVO
import com.mth.mycomposestarterkit.data.getMovieList
import com.mth.mycomposestarterkit.data.getServices
import com.mth.mycomposestarterkit.ui.theme.White
import com.mth.mycomposestarterkit.ui.theme.White87
import com.mth.mycomposestarterkit.ui.theme.Yellow

/**
 * @Author myothiha
 * Created 15/02/2024 at 1:53 PM.
 **/
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState()
) {

    LazyColumn(
        modifier = modifier
            .padding(bottom = 80.dp)
            .fillMaxSize()
            .background(color = Color.Black),
        state = state,
        contentPadding = PaddingValues(0.dp)
    ) {
        item {
            HeaderSection(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            SearchMovieSection(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                leadingIcon = Icons.Default.Search
            )

            //CarouselMovieSection()

            LabelAndContentSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 16.dp),
                title = "Coming Soon",
                isSeeAllShowed = true
            ) {
                HorizontalTitleSection(
                    modifier = Modifier
                        .fillMaxWidth(),
                    list = getMovieList(),
                    usedFlingBehavior = false
                ) { movie ->

                    ComingSoonMovieView(
                        modifier = Modifier
                            .wrapContentHeight().animateItemPlacement(

                            ),
                        data = movie,
                        navController = navController
                    )
                }

            }



            LabelAndContentSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                title = "Promotion & Discount",
                isSeeAllShowed = true
            ) {
                PromotionTitleSection(
                    modifier = Modifier
                        .fillMaxWidth(),
                    list = getMovieList(),
                    usedFlingBehavior = true
                )
            }

            LabelAndContentSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 16.dp),
                title = "Service",
                isSeeAllShowed = true,
            ) {
                HorizontalTitleSection(
                    modifier = Modifier
                        .fillMaxWidth(),
                    list = getServices(),
                    usedFlingBehavior = false
                ) { service ->
                    ServiceView(
                        modifier = Modifier
                            .wrapContentHeight(),
                        data = service
                    )
                }

            }

            LabelAndContentSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                title = "Movie news",
                isSeeAllShowed = true
            ) {
                HorizontalTitleSection(
                    modifier = Modifier
                        .fillMaxWidth(),
                    list = getMovieList(),
                    usedFlingBehavior = false
                ) { movie ->
                    MovieNewsView(
                        modifier = Modifier
                            .wrapContentHeight(),
                        data = movie
                    )
                }

            }


        }
    }
}

@Composable
fun SearchMovieSection(modifier: Modifier = Modifier, leadingIcon: ImageVector) {
    Row(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color.Gray.copy(alpha = 0.2f)),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color(0xFF8C8C8C)),
            value = "",
            onValueChange = {},
            leadingIcon = {
                Icon(imageVector = leadingIcon, contentDescription = null, tint = White)
            },
            placeholder = { Text(text = "Search", color = White) })
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PromotionTitleSection(
    modifier: Modifier = Modifier,
    list: List<MovieVO>,
    usedFlingBehavior: Boolean,
    state: LazyListState = rememberLazyListState()
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
            PromotionAndMovieView(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .wrapContentHeight(),
                data = data
            )
        }
    }

}

@Composable
fun HeaderSection(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(0.8f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Hi,Myo Thiha",
                color = White,
                fontSize = 16.sp,
                lineHeight = 21.sp
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Welcome back",
                color = Color.White,
                fontSize = 20.sp,
                lineHeight = 20.sp
            )
        }

        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = null
        )
    }
}

@Composable
fun ComingSoonMovieView(
    modifier: Modifier = Modifier,
    data: MovieVO,
    navController: NavController
) {
    Column(modifier = modifier) {
        Card {
            MovieImageView(
                modifier = Modifier
                    .width(180.dp)
                    .height(240.dp)
                    .clickable {
                        navController.navigate(route = Destination.MovieDetailScreen.title)
                    },
                data = data.imageUrl
            )
        }
        Text(
            modifier = Modifier.padding(vertical = 12.dp),
            text = data.title,
            color = Yellow,
            fontSize = 18.sp,
            fontWeight = FontWeight(200)
        )
        IconTextView(
            text = "Adventure, Sci-fi",
            drawableRes = R.drawable.video,
            textColor = White87
        )
        IconTextView(
            text = data.date,
            drawableRes = R.drawable.ic_calendar,
            textColor = White87
        )

    }
}

@Composable
fun MovieNewsView(
    modifier: Modifier = Modifier,
    data: MovieVO,
) {
    Column(modifier = modifier) {
        Card {
            MovieImageView(
                modifier = Modifier
                    .width(239.dp)
                    .height(186.dp),
                data = data.imageUrl
            )
        }
        Text(
            modifier = Modifier.padding(vertical = 12.dp),
            text = data.title,
            color = White,
            fontSize = 16.sp,
            fontWeight = FontWeight(200),
            lineHeight = 12.sp
        )
    }
}


@Composable
fun LazyItemScope.PromotionAndMovieView(
    modifier: Modifier = Modifier,
    data: MovieVO,
) {
    Column(modifier = modifier) {
        Card(shape = RoundedCornerShape(12.dp)) {
            MovieImageView(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .height(224.dp),
                data = data.imageUrl
            )
        }
    }
}

@Composable
fun ServiceView(
    modifier: Modifier = Modifier,
    data: ServiceVO,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(shape = CircleShape) {
            MovieImageView(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                data = data.image
            )
        }
        Text(
            modifier = Modifier
                .width(100.dp)
                .padding(top = 12.dp),
            text = data.name,
            color = White,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HomeScreen()
    }
}

@Composable
@Preview(showBackground = true)
fun ComingSoonMoviePreview() {
    Column(
        modifier = Modifier
            .wrapContentSize()
    ) {
        /*ComingSoonMovieView(
            data = MovieVO(
                id = 1,
                title = "No Time to die",
                imageUrl = "https://image.tmdb.org/t/p/original/eqWaeh21e4ZgHjwpULZVHCGIq9X.jpg",
                date = "November 2021"
            ),
            navController = rememberNavController()
        )*/
    }
}

@Composable
@Preview(showBackground = true)
fun ServicePreview() {
    Column(
        modifier = Modifier
            .wrapContentSize()
    ) {
        ServiceView(
            data = ServiceVO(
                id = 1,
                name = "No Time to die",
                image = R.drawable.discount,
            )
        )
    }
}


@Composable
@Preview(showBackground = true)
fun SearchMoviePreview() {
    Column(
        modifier = Modifier
            .wrapContentSize()
    ) {
        // SearchMovieSection()
    }
}
