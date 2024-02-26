package com.mth.mycomposestarterkit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mth.mycomposestarterkit.components.HorizontalTitleSection
import com.mth.mycomposestarterkit.components.LabelAndContentSection
import com.mth.mycomposestarterkit.components.MovieImageView
import com.mth.mycomposestarterkit.data.CinemaVO
import com.mth.mycomposestarterkit.data.PersonVO
import com.mth.mycomposestarterkit.data.getCinemas
import com.mth.mycomposestarterkit.data.getPersons
import com.mth.mycomposestarterkit.ui.theme.Black
import com.mth.mycomposestarterkit.ui.theme.White
import com.mth.mycomposestarterkit.ui.theme.White75
import com.mth.mycomposestarterkit.ui.theme.Yellow
import kotlin.math.ceil
import kotlin.math.floor


/**
 * @Author myothiha
 * Created 22/02/2024 at 1:07 PM.
 **/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            MovieHeaderInfoSection()

            Spacer(modifier = Modifier.height(18.dp))

            MovieInfoSection()

            Spacer(modifier = Modifier.height(18.dp))

            LabelAndContentSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                title = "Director",
                isSeeAllShowed = false
            ) {
                HorizontalTitleSection(
                    modifier = Modifier
                        .fillMaxWidth(),
                    list = getPersons(),
                    usedFlingBehavior = false
                ) { person ->
                    DirectorAndActorItem(data = person)
                }

            }
            Spacer(modifier = Modifier.height(18.dp))

            LabelAndContentSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                title = "Actor",
                isSeeAllShowed = false
            ) {
                HorizontalTitleSection(
                    modifier = Modifier
                        .fillMaxWidth(),
                    list = getPersons(),
                    usedFlingBehavior = false
                ) { person ->
                    DirectorAndActorItem(data = person)
                }
            }


        }
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 6.dp),
                text = "Cinema",
                color = Color.White,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                ),
                fontSize = 20.sp,
                lineHeight = 30.sp
            )
        }
        items(
            items = getCinemas(),
            key = { cinema -> cinema.id }
        ) { cinema ->
            CinemaItem(data = cinema)
        }
        item {
            ElevatedButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Yellow),
                onClick = { }
            ) {
                Text(
                    text = "Continue",
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    color = Color.Black
                )
            }
        }
    }


}

@Composable
fun DirectorAndActorItem(modifier: Modifier = Modifier, data: PersonVO) {
    Card(
        modifier = modifier
            .wrapContentHeight(),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Black

    ) {
        Row(
            modifier = Modifier
                .width(150.dp)
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            MovieImageView(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape),
                data = data.image
            )
            Column {
                Text(
                    modifier = Modifier,
                    text = data.name,
                    color = White,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                )
            }

        }

    }

}

@Composable
fun CinemaItem(modifier: Modifier = Modifier, data: CinemaVO,horizontalPadding : Dp = 16.dp) {
    Row(
        modifier = modifier
            .padding(horizontal = horizontalPadding)
            .padding(bottom = 4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(12.dp))
            .background(color = if (data.isClicked) Yellow.copy(alpha = 0.1f) else Black)
            .border(
                width = 1.dp,
                color = if (data.isClicked) Yellow else Color.Transparent,
                RoundedCornerShape(12.dp)
            )
            .clickable {

            }
    ) {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier,
                text = data.name,
                color = White,
                fontSize = 18.sp,
                lineHeight = 20.sp,
            )
            Text(
                modifier = Modifier,
                text = "${data.distance}km | ${data.address}",
                color = White,
                fontSize = 14.sp,
            )
        }
        MovieImageView(modifier = Modifier.padding(12.dp), data = data.image)
    }
}

@Composable
private fun MovieHeaderInfoSection() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (backBtn, coverImage, movieInfo, text) = createRefs()
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(241.dp)
                .constrainAs(coverImage) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                },
            painter = painterResource(id = R.drawable.discount),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Icon(
            modifier = Modifier
                .padding(all = 16.dp)
                .size(24.dp)
                .constrainAs(backBtn) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                },
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.White
        )
        MovieNameCard(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 40.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(movieInfo) {
                    top.linkTo(coverImage.bottom)
                    bottom.linkTo(coverImage.bottom)
                },
        )
    }
}

@Composable
fun MovieNameCard(modifier: Modifier = Modifier) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Black

    ) {
        Column(
            modifier = Modifier
        ) {
            MovieTitle()

            Spacer(modifier = Modifier.height(25.dp))

            ReviewRate()

            RatingBar(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp))

        }
    }
}

@Composable
fun MovieInfoSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HorizontalTextView(text = "Movie genre:", subText = "Action,Adventure,Sci-fi")
        HorizontalTextView(text = "Censorship:", subText = "13+")
        HorizontalTextView(text = "Language:", subText = "English")
        Text(
            text = "Story Line",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 6.dp),
            style = TextStyle(
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            ),
            fontSize = 22.sp,
            lineHeight = 30.sp,
            color = White
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos.... See more",
            color = White,
            fontSize = 16.sp,
        )
    }
}

@Composable
fun HorizontalTextView(text: String, subText: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier,
            fontSize = 16.sp,
            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
            color = White75
        )
        Text(
            text = subText,
            modifier = Modifier,
            fontSize = 16.sp,
            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
            color = White
        )
    }

}

@Composable
fun MovieTitle() {
    Text(
        text = "No Time No Die ",
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp),
        style = TextStyle(
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        fontSize = 22.sp,
        color = White,
        fontWeight = FontWeight.Bold
    )

    Text(
        text = "2h29m * Action, adventure * sci-fi",
        modifier = Modifier
            .padding(horizontal = 16.dp),
        fontSize = 16.sp,
        style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
        color = White75,

        )
}

@Composable
fun ReviewRate() {
    Row {
        Text(
            text = "Review",
            modifier = Modifier
                .padding(horizontal = 16.dp),
            fontSize = 18.sp,
            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
            color = White
        )
        Image(
            painter = painterResource(id = R.drawable.ic_rating),
            contentDescription = null
        )
        Text(
            text = "4.8",
            modifier = Modifier
                .padding(horizontal = 10.dp),
            fontSize = 18.sp,
            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
            color = White
        )
        Text(
            text = "(1.222)",
            modifier = Modifier,
            fontSize = 16.sp,
            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
            color = White75
        )

    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Gray,
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
        }
        /*if (halfStar) {
            Icon(
                imageVector = Icons.Filled.StarHalf,
                contentDescription = null,
                tint = starsColor
            )
        }*/
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DetailScreenPreview() {
    Column(
        modifier = Modifier
            .wrapContentSize()
    ) {
        DetailScreen()
    }
}

