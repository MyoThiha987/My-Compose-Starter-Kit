package com.mth.mycomposestarterkit

/**
 * @Author myothiha
 * Created 29/02/2024 at 10:45 PM.
 **/

sealed class Destination(val title : String) {
    data object HomeScreen : Destination(title ="Home")
    data object TicketScreen : Destination(title ="Ticket")
    data object MoviesScreen : Destination(title ="Movies")
    data object AccountScreen : Destination(title ="Account")
    data object MovieDetailScreen : Destination(title ="Detail")

}