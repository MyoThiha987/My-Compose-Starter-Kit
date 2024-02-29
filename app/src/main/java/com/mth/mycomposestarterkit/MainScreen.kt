package com.mth.mycomposestarterkit

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * @Author myothiha
 * Created 29/02/2024 at 10:04 PM.
 **/


@Composable
fun MainScreen(
    navController : NavHostController = rememberNavController()
){

    Scaffold(
        containerColor = Color.Black,
        bottomBar = {
            BottomNavigationView(navController = navController)
        }
    ) {
        AppNavHost(navHostController = navController, paddingValues = it)
    }
}