package com.mth.mycomposestarterkit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mth.mycomposestarterkit.ui.theme.DarkBlue
import com.mth.mycomposestarterkit.ui.theme.Grey
import com.mth.mycomposestarterkit.ui.theme.Grey30

/**
 * @Author myothiha
 * Created 15/02/2024 at 1:53 PM.
 **/
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberLazyListState()
    val movies = mutableListOf(
        MovieVO(
            id = 1,
            title = "No Time to die",
            imageUrl = "https://image.tmdb.org/t/p/original/eqWaeh21e4ZgHjwpULZVHCGIq9X.jpg",
            date = "November 2021"
        ),
        MovieVO(
            id = 2,
            title = "Shang Chi",
            imageUrl = "https://image.tmdb.org/t/p/original/1BIoJGKbXjdFDAqUEiA2VHqkK1Z.jpg",
            date = "November 2021"
        ),
        MovieVO(
            id = 1,
            title = "No Time to die",
            imageUrl = "https://image.tmdb.org/t/p/original/eqWaeh21e4ZgHjwpULZVHCGIq9X.jpg",
            date = "November 2021"
        ),
        MovieVO(
            id = 2,
            title = "Shang Chi",
            imageUrl = "https://image.tmdb.org/t/p/original/1BIoJGKbXjdFDAqUEiA2VHqkK1Z.jpg",
            date = "November 2021"
        )
    )
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = DarkBlue),
        state = scrollState,
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            HeaderSection(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            SearchSection(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Grey30)
                    .padding(15.dp, 18.dp, 15.dp, 18.dp)
            )

            MovieSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 12.dp),
                title = "Coming Soon",
                isSeeAllShowed = false,
                content = {
                    ComingSoonHorizontalMovieSection(
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            )

            MovieSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 12.dp),
                title = "Cinema Near You",
                isSeeAllShowed = true,
            )
        }


        items(items = movies) {
            CinemaNearYouMovieView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 12.dp),
                data = it
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
                text = "Welcome Back,",
                color = Grey,
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                lineHeight = 21.sp
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Myo Thiha",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight(500),
                lineHeight = 27.sp
            )
        }
        Image(
            modifier = Modifier.size(48.dp),
            painter = painterResource(id = R.drawable.profile),
            contentDescription = null
        )
    }
}

@Composable
fun SearchSection(modifier: Modifier = Modifier) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = Grey
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Search your favourite movies",
            color = Grey,
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            lineHeight = 21.sp
        )
    }
}

@Composable
fun MovieSection(
    modifier: Modifier = Modifier,
    title: String,
    isSeeAllShowed: Boolean = false,
    content: @Composable () -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(0.8f),
                text = title,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                lineHeight = 30.sp
            )

            AnimatedVisibility(
                visible = isSeeAllShowed,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkHorizontally()
            ) {
                Text(
                    modifier = Modifier.wrapContentSize(),
                    text = "See all",
                    color = Grey,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(300),
                    lineHeight = 21.sp
                )
            }


        }
        content()
    }

}

@Composable
fun ComingSoonHorizontalMovieSection(
    modifier: Modifier = Modifier
) {


    val movies = mutableListOf(
        MovieVO(
            id = 1,
            title = "No Time to die",
            imageUrl = "https://image.tmdb.org/t/p/original/eqWaeh21e4ZgHjwpULZVHCGIq9X.jpg",
            date = "November 2021"
        ),
        MovieVO(
            id = 2,
            title = "Shang Chi",
            imageUrl = "https://image.tmdb.org/t/p/original/1BIoJGKbXjdFDAqUEiA2VHqkK1Z.jpg",
            date = "November 2021"
        ),
        MovieVO(
            id = 1,
            title = "No Time to die",
            imageUrl = "https://image.tmdb.org/t/p/original/eqWaeh21e4ZgHjwpULZVHCGIq9X.jpg",
            date = "November 2021"
        ),
        MovieVO(
            id = 2,
            title = "Shang Chi",
            imageUrl = "https://image.tmdb.org/t/p/original/1BIoJGKbXjdFDAqUEiA2VHqkK1Z.jpg",
            date = "November 2021"
        )
    )
    LazyRow(
        state = rememberLazyListState(),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(items = movies) { movie ->
            ComingSoonMovieView(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .wrapContentHeight(),
                data = movie
            )
        }
    }

}

@Composable
fun ComingSoonMovieView(
    modifier: Modifier = Modifier,
    data: MovieVO,
) {


    Column(modifier = modifier) {
        Card {
            MovieImageView(
                modifier = Modifier
                    .height(180.dp),
                data = data.imageUrl
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            text = data.title,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight(600),
            lineHeight = 30.sp
        )

    }


}

@Composable
fun CinemaNearYouMovieView(
    modifier: Modifier = Modifier,
    data: MovieVO,
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row {
            MovieImageView(
                modifier = Modifier
                    .size(100.dp),
                data = data.imageUrl
            )

        }

    }

}

@Composable
fun MovieImageView(modifier: Modifier = Modifier, data: String) {
    AsyncImage(
        model = data,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop,
    )
}

data class MovieVO(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val date: String
)


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
