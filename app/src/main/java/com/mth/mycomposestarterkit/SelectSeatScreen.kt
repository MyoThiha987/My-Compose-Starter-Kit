package com.mth.mycomposestarterkit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mth.mycomposestarterkit.components.HorizontalTitleSection
import com.mth.mycomposestarterkit.components.MovieImageView
import com.mth.mycomposestarterkit.data.DateTimeVO
import com.mth.mycomposestarterkit.data.SeatVO
import com.mth.mycomposestarterkit.data.getAvailableDateTimeList
import com.mth.mycomposestarterkit.data.getSeats
import com.mth.mycomposestarterkit.ui.theme.Black
import com.mth.mycomposestarterkit.ui.theme.BlackOrange
import com.mth.mycomposestarterkit.ui.theme.Grey100
import com.mth.mycomposestarterkit.ui.theme.White
import com.mth.mycomposestarterkit.ui.theme.White75
import com.mth.mycomposestarterkit.ui.theme.Yellow

/**
 * @Author myothiha
 * Created 23/02/2024 at 1:33 PM.
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectSeatScreen(modifier: Modifier = Modifier) {
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
                        text = "Select Seat",
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

        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = rememberLazyListState(),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .weight(0.7f)
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                // TV Destination
                item {
                    MovieImageView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp),
                        data = R.drawable.ic_screen
                    )
                    SeatSection()
                    Spacer(modifier = Modifier.height(12.dp))
                    AvailableSeatSection()
                    Spacer(modifier = Modifier.height(18.dp))
                    SelectDateAndTimeSection()
                }

            }

            HorizontalDivider(thickness = 1.dp, color = Black)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp,vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "Total",
                        textAlign = TextAlign.Center,
                        color = White,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "210.000VND",
                        textAlign = TextAlign.Center,
                        color = Yellow,
                        fontSize = 24.sp
                    )
                }

                ElevatedButton(
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Yellow),
                    onClick = { }
                ) {
                    Text(
                        text = "Buy ticket",
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                }
            }
        }

    }


}

@Composable
fun SelectDateAndTimeSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Select Date & Time",
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 18.sp
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    HorizontalTitleSection(
        list = getAvailableDateTimeList(),
        usedFlingBehavior = false,
        state = rememberLazyListState()
    ) {
        AvailableDateItemView(data = it)

    }
    Spacer(modifier = Modifier.height(24.dp))
    HorizontalTitleSection(
        list = getAvailableDateTimeList()[0].time,
        usedFlingBehavior = false,
        state = rememberLazyListState()
    ) {
        AvailableTimeItemView(data = it, isSelected = getAvailableDateTimeList()[4].isSelected)

    }

}

@Composable
fun AvailableTimeItemView(modifier: Modifier = Modifier, data: String, isSelected: Boolean) {
    Text(
        text = data,
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .background(color = if (isSelected) Yellow.copy(alpha = 0.2f) else Black)
            .border(
                width = 1.dp, color = if (isSelected) Yellow else Color.Transparent,
                RoundedCornerShape(30.dp)
            )
            .padding(horizontal = 22.dp, vertical = 8.dp),
        textAlign = TextAlign.Center,
        color = if (isSelected) White else White75
    )


}

@Composable
fun AvailableDateItemView(modifier: Modifier = Modifier, data: DateTimeVO) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(30.dp))
            .background(
                color = if (data.isSelected) Yellow else Black,
                shape = RoundedCornerShape(30.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 14.dp, horizontal = 12.dp),
            text = data.month,
            textAlign = TextAlign.Center,
            color = if (data.isSelected) Black else White75,
            fontSize = 16.sp
        )
        Text(
            text = data.day,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .drawBehind {
                    drawCircle(
                        color = if (data.isSelected) Black else Grey100,
                        radius = this.size.maxDimension
                    )
                },
            fontSize = 16.sp,
            color = if (data.isSelected) White else White75
        )
    }

}

@Composable
fun AvailableSeatView(
    modifier: Modifier = Modifier,
    availableColor: Color,
    status: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier
                .size(24.dp)
                .background(color = availableColor, shape = RoundedCornerShape(4.dp))
        )
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = status,
            fontSize = 14.sp,
            color = White75,
            style = TextStyle(
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun AvailableSeatSection(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        AvailableSeatView(availableColor = Black, status = "Available")
        AvailableSeatView(availableColor = BlackOrange, status = "Reserved")
        AvailableSeatView(availableColor = Yellow, status = "Selected")
    }
}

@Composable
fun SeatItemView(modifier: Modifier = Modifier, data: SeatVO, width: Dp) {
    Box(
        modifier = modifier
            .size(width)
            .background(
                color = if (data.status == 2) Yellow else if (data.status == 1) BlackOrange else Black,
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        Text(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .padding(vertical = 8.dp),
            text = data.no,
            fontSize = 10.sp,
            color = if (data.status == 2) Black else if (data.status == 1) Yellow else White75,
            style = TextStyle(
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            ),
            textAlign = TextAlign.Center
        )
    }


}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SeatSection(
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth =
        (screenWidth / 12).coerceAtMost(screenWidth) // Calculate the width for each item
    //Seat
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.Top),
        horizontalArrangement = Arrangement.spacedBy(
            5.dp,
            alignment = Alignment.CenterHorizontally
        ),
        maxItemsInEachRow = 12
    ) {
        repeat(getSeats().size) {
            SeatItemView(data = getSeats()[it], width = itemWidth)
        }
    }

}

@Composable
@Preview()
fun SelectSeatScreenPreview() {
    SelectSeatScreen()
}

@Composable
@Preview
fun AvailableDateItemViewPreview() {
    AvailableDateItemView(
        data = DateTimeVO(
            id = 0,
            month = "Dec",
            day = "01",
            time = listOf("09:30", "11:05", "14:15", "16:30", "20:05", "23:15", "01:30")
        )
    )
}



