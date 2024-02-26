package com.mth.mycomposestarterkit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mth.mycomposestarterkit.components.IconTextView
import com.mth.mycomposestarterkit.components.MovieImageView
import com.mth.mycomposestarterkit.data.MenuVO
import com.mth.mycomposestarterkit.data.getMenu
import com.mth.mycomposestarterkit.ui.theme.White
import com.mth.mycomposestarterkit.ui.theme.White87

/**
 * @Author myothiha
 * Created 26/02/2024 at 11:53 PM.
 **/

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            ProfileItemView()
        }
        itemsIndexed(
            items = getMenu()
        ) { index, menu ->
            val isLastIndex = (index < getMenu().lastIndex)

            MenuItemView(data = menu, isLastIndex = isLastIndex)
        }

    }

}

@Composable
fun ProfileItemView(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(vertical = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Row(
            modifier = Modifier.weight(0.7f),
            horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            MovieImageView(
                data = R.drawable.profile,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(color = Color.Transparent, shape = CircleShape)
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = "Myo Thiha",
                    color = White,
                    fontSize = 24.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                IconTextView(
                    text = "Adventure, Sci-fi", drawableRes = R.drawable.ic_call, textColor = White87
                )
                IconTextView(
                    text = "Vincom Ocean Park CGV",
                    drawableRes = R.drawable.ic_sms,
                    textColor = White87
                )

            }
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_edit),
            contentDescription = null,
            tint = White87
        )

    }

}

@Composable
fun MenuItemView(
    modifier: Modifier = Modifier, data: MenuVO, isLastIndex: Boolean
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.weight(0.7f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    painter = painterResource(id = data.icon),
                    contentDescription = null,
                    tint = White87
                )
                Text(
                    modifier = Modifier,
                    text = data.name,
                    color = White,
                    fontSize = 14.sp,
                )
            }
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_arrow_forward),
                contentDescription = null,
                tint = White87
            )

        }
        if (isLastIndex) HorizontalDivider(
            thickness = 1.dp,
            color = Color.Gray,
            // modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
@Preview
fun ProfileScreenPreview() {
    ProfileScreen()
}