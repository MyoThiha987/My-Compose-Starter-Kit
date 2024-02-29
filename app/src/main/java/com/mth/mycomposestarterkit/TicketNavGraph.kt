package com.mth.mycomposestarterkit

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

/**
 * @Author myothiha
 * Created 29/02/2024 at 10:16 PM.
 **/

fun NavGraphBuilder.ticketNavGraph(
    navHostController: NavHostController
) {
    navigation(startDestination = Destination.TicketScreen.title, route = Graph.TICKET) {
        composable(route = Destination.TicketScreen.title) {
            TicketListScreen()
        }
    }
}