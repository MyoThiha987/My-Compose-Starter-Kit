package com.mth.mycomposestarterkit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mth.mycomposestarterkit.components.IconTextView
import com.mth.mycomposestarterkit.components.MovieImageView
import com.mth.mycomposestarterkit.data.CinemaVO
import com.mth.mycomposestarterkit.data.MovieVO
import com.mth.mycomposestarterkit.data.getCinemas
import com.mth.mycomposestarterkit.ui.theme.Black
import com.mth.mycomposestarterkit.ui.theme.White
import com.mth.mycomposestarterkit.ui.theme.White87
import com.mth.mycomposestarterkit.ui.theme.Yellow

/**
 * @Author myothiha
 * Created 25/02/2024 at 11:09 PM.
 **/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(modifier: Modifier = Modifier) {
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
                        text = "Payment",
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            )
        }
    ) { padding ->
        val movie = MovieVO(
            id = 1,
            title = "No Time to die",
            imageUrl = "https://image.tmdb.org/t/p/original/eqWaeh21e4ZgHjwpULZVHCGIq9X.jpg",
            date = "November 2021"
        )
        Column (modifier=Modifier.fillMaxWidth().padding(16.dp)){
            LazyColumn(
                modifier = Modifier
                    .weight(0.7f),
                contentPadding = padding,
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                item {
                    MovieInfoItemView(data = movie)
                    SpaceBetweenTextView(
                        text = "Order ID",
                        subText = "78889377726",
                        subTextColor = White,
                        subTextFontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    SpaceBetweenTextView(
                        text = "Seat",
                        subText = "H7, H8",
                        subTextColor = White,
                        subTextFontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    DiscountItemView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        leadingIcon = Icons.Default.Search
                    )
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Black,
                        modifier = Modifier.padding(vertical = 24.dp)
                    )
                    SpaceBetweenTextView(
                        text = "Total",
                        subText = "210.000VND",
                        subTextColor = Yellow,
                        subTextFontSize = 24.sp
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 4.dp),
                        text = "Payment Method",
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
                    PaymentMethodItemView(data = cinema, horizontalPadding = 0.dp)
                }
                item {
                    SpaceBetweenTextView(
                        modifier = Modifier
                            .background(
                                color = Yellow.copy(alpha = 0.1f),
                                RoundedCornerShape(12.dp)
                            )
                            .padding(12.dp),
                        text = "Complete your payment in",
                        subText = "15:00",
                        subTextColor = Yellow
                    )
                }
            }
            ElevatedButton(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Yellow),
                onClick = { }
            ) {
                Text(
                    text = "Purchase",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun PaymentMethodItemView(
    modifier: Modifier = Modifier,
    data: CinemaVO,
    horizontalPadding: Dp = 16.dp
) {
    Row(
        modifier = modifier
            .padding(horizontal = horizontalPadding)
            .padding(bottom = 6.dp)
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

            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .weight(0.8f)
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovieImageView(
                modifier = Modifier
                    .width(70.dp)
                    .height(40.dp),
                data = R.drawable.zalo_pay
            )
            Text(
                modifier = Modifier,
                text = "Zalo Pay",
                color = White,
                fontSize = 14.sp,
            )
        }
        MovieImageView(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .size(24.dp),
            data = R.drawable.ic_arrow_forward
        )
    }
}

@Composable
fun DiscountItemView(modifier: Modifier = Modifier, leadingIcon: ImageVector) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(45.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Black)
    ) {
        TextField(
            modifier = Modifier
                .weight(0.7f)
                .background(color = Black),
            shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            value = "",
            onValueChange = {},
            leadingIcon = {
                androidx.compose.material.Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = White
                )
            },
            placeholder = {
                Text(
                    text = "discount code",
                    color = White,
                    fontSize = 12.sp,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    ),
                )
            })

        ElevatedButton(
            modifier = Modifier
                .wrapContentWidth()
                .height(45.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Yellow),
            onClick = { }
        ) {
            Text(
                text = "Apply",
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }

}

@Composable
fun SpaceBetweenTextView(
    modifier: Modifier = Modifier,
    text: String,
    subText: String,
    subTextColor: Color,
    subTextFontSize: TextUnit = 16.sp
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier,
            text = text,
            color = White,
            fontSize = 16.sp,
        )
        Text(
            modifier = Modifier,
            text = subText,
            color = subTextColor,
            fontSize = subTextFontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun MovieInfoItemView(modifier: Modifier = Modifier, data: MovieVO) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Black, shape = RoundedCornerShape(16.dp)),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MovieImageView(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    , data = data.imageUrl
            )

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
                    maxLines = 1,
                    fontWeight = FontWeight.Bold
                )
                IconTextView(
                    text = "Adventure, Sci-fi",
                    drawableRes = R.drawable.video,
                    textColor = White87
                )
                IconTextView(
                    text = "Vincom Ocean Park CGV",
                    drawableRes = R.drawable.ic_location,
                    textColor = White87
                )
                IconTextView(
                    text = "10.12.2022 • 14:15",
                    drawableRes = R.drawable.ic_clock,
                    textColor = White87
                )
            }
        }


    }
}

@Composable
@Preview
fun PaymentScreenPreview() {
    PaymentScreen()
}