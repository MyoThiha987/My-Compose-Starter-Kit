package com.mth.mycomposestarterkit

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

/**
 * @Author myothiha
 * Created 29/02/2024 at 10:15 PM.
 **/

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(navController = navHostController, startDestination = Graph.HOME) {
        homeNavGraph(navHostController = navHostController)
        ticketNavGraph(navHostController = navHostController)
        moviesNavGraph(navHostController = navHostController)
        accountNavGraph(navHostController = navHostController)
        composable(route= Destination.MovieDetailScreen.title){
            DetailScreen()

        }

    }
}


object Graph {
    const val HOME = "home"
    const val TICKET = "ticket"
    const val MOVIES = "movies"
    const val ACCOUNT = "account"
}