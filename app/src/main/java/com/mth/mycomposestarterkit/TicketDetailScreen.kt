package com.mth.mycomposestarterkit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mth.mycomposestarterkit.components.DashLine
import com.mth.mycomposestarterkit.components.IconTextView
import com.mth.mycomposestarterkit.components.MovieImageView
import com.mth.mycomposestarterkit.data.MovieVO
import com.mth.mycomposestarterkit.ui.theme.Black
import com.mth.mycomposestarterkit.ui.theme.Yellow

/**
 * @Author myothiha
 * Created 26/02/2024 at 3:19 PM.
 **/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketDetailScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                modifier = Modifier,
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black),
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "My Ticket",
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                .padding(12.dp)
                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val data = MovieVO(
                id = 1,
                title = "No Time to die",
                imageUrl = "https://image.tmdb.org/t/p/original/eqWaeh21e4ZgHjwpULZVHCGIq9X.jpg",
                date = "November 2021"
            )
            MovieInformationContainer(
                data = data,
                roundedCornerShape = true
            ) {
                MovieInformationView(
                    data = it,
                    textColor = Black,
                    iconTintColor = Black
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconWithColumnText(
                    textOne = "14h15’",
                    textTwo = "10.12.2022",
                    drawableRes = R.drawable.ic_clock,
                    iconTintColor = Color.Black
                )
                IconWithColumnText(
                    textOne = "142h15’",
                    textTwo = "10.12.2022",
                    drawableRes = R.drawable.ic_clock,
                    iconTintColor = Color.Black
                )
            }
            HorizontalDivider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            IconTextView(
                text = "210.000 VND",
                drawableRes = R.drawable.ic_clock,
                textColor = Color.Black,
                iconTintColor = Color.Black
            )
            IconTextView(
                text = "Adventure, Sci-fi",
                drawableRes = R.drawable.video,
                textColor = Color.Black,
                iconTintColor = Color.Black,
                containsImage = true,
                containSubText = true,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            IconTextView(
                text = "Show this QR code to the ticket counter to receive your ticket",
                drawableRes = R.drawable.video,
                textColor = Color.Black,
                iconTintColor = Color.Black
            )
            TicketView()
            Spacer(modifier = Modifier.height(4.dp))
            MovieImageView(
                data = R.drawable.barcode,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Oder ID: 78889377726",
                fontSize = 14.sp,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                ),
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
fun IconWithColumnText(
    drawableRes: Int,
    textOne: String,
    textTwo: String,
    modifier: Modifier = Modifier,
    iconTintColor: Color = Color.White
) {
    Row(
        modifier = modifier.wrapContentSize(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = drawableRes),
            contentDescription = null,
            tint = iconTintColor
        )
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                textOne, fontSize = 14.sp,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                ),
            )
            Text(
                textTwo, fontSize = 14.sp,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                ),
            )
        }
    }
}

@Composable
fun <T> MovieInformationContainer(
    roundedCornerShape: Boolean = false,
    modifier: Modifier = Modifier,
    data: T,
    content: @Composable (T) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val imageUrl: (T) -> String = { (it as MovieVO).imageUrl }
        Card(
            shape = if (roundedCornerShape) RoundedCornerShape(16.dp) else RoundedCornerShape(
                topStart = 16.dp,
                bottomStart = 16.dp
            )
        ) {
            MovieImageView(
                modifier = Modifier
                    .width(100.dp)
                    .height(141.dp),
                data = imageUrl(data)
            )
        }
        content(data)

    }
}

@Composable
fun MovieInformationView(
    data: MovieVO,
    iconTintColor: Color = Color.White,
    textColor: Color = Color.White,
) {
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier,
            text = data.title,
            color = Yellow,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        IconTextView(
            text = "10.12.2022 • 14:15",
            drawableRes = R.drawable.ic_clock,
            textColor = textColor,
            iconTintColor = iconTintColor
        )
        IconTextView(
            text = "Adventure, Sci-fi",
            drawableRes = R.drawable.video,
            textColor = textColor,
            iconTintColor = iconTintColor
        )
    }
}

@Composable
fun TicketView(
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (left, right, line) = createRefs()
        Box(
            modifier = Modifier
                .size(15.dp)
                .drawBehind {
                    drawCircle(
                        color = Color.Black,
                        radius = this.size.maxDimension
                    )
                }
                .constrainAs(left) {
                    top.linkTo(right.top)
                    bottom.linkTo(right.bottom)
                    start.linkTo(parent.start, margin = (-15).dp)
                },
        )
        DashLine(modifier = Modifier
            .constrainAs(line) {
                top.linkTo(left.top)
                bottom.linkTo(left.bottom)
                start.linkTo(left.start)
                end.linkTo(right.start)
            })
        Box(
            modifier = Modifier
                .size(15.dp)
                .drawBehind {
                    drawCircle(
                        color = Color.Black,
                        radius = this.size.maxDimension
                    )
                }
                .constrainAs(right) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end, -15.dp)
                }
        )

    }

}

@Composable
@Preview
fun TicketDetailScreenPreview() {
    TicketDetailScreen()
}