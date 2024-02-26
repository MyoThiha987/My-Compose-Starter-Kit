package com.mth.mycomposestarterkit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mth.mycomposestarterkit.data.getMovieList
import com.mth.mycomposestarterkit.ui.theme.Black

/**
 * @Author myothiha
 * Created 26/02/2024 at 10:35 PM.
 **/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketListScreen(modifier: Modifier = Modifier) {
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
        LazyColumn(
            state = rememberLazyListState(),
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                items = getMovieList(),
                key = { movie -> movie.id }
            ) {
                MovieInformationContainer(
                    roundedCornerShape = false,
                    data = it,
                    modifier = Modifier.background(color = Black, shape = RoundedCornerShape(12.dp))
                ) {
                    MovieInformationView(
                        data = it,
                        textColor = Color.White,
                        iconTintColor = Color.White
                    )

                }

            }
        }
    }

}

@Composable
@Preview
fun TicketListScreenPreview() {
    TicketListScreen()
}