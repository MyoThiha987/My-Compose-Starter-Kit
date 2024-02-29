package com.mth.mycomposestarterkit

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

/**
 * @Author myothiha
 * Created 29/02/2024 at 10:16 PM.
 **/

fun NavGraphBuilder.moviesNavGraph(
    navHostController: NavHostController
) {
    navigation(startDestination = Destination.MoviesScreen.title, route = Graph.MOVIES) {
        composable(route = Destination.MoviesScreen.title) {
            MoviesScreen()
        }
    }
}