package com.mth.mycomposestarterkit

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

/**
 * @Author myothiha
 * Created 29/02/2024 at 10:16 PM.
 **/

fun NavGraphBuilder.homeNavGraph(
    navHostController: NavHostController
) {
    navigation(startDestination = Destination.HomeScreen.title, route = Graph.HOME) {
        composable(route = Destination.HomeScreen.title) {
            HomeScreen(navController = navHostController)
        }
    }
}