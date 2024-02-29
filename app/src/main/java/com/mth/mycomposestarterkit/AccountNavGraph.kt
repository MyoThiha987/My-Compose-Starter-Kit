package com.mth.mycomposestarterkit

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

/**
 * @Author myothiha
 * Created 29/02/2024 at 10:17 PM.
 **/

fun NavGraphBuilder.accountNavGraph(
    navHostController: NavHostController
) {
    navigation(startDestination = Destination.AccountScreen.title, route = Graph.ACCOUNT) {
        composable(route = Destination.AccountScreen.title) {
            ProfileScreen()
        }
    }
}