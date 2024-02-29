package com.mth.mycomposestarterkit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mth.mycomposestarterkit.data.BottomNavItem
import com.mth.mycomposestarterkit.data.bottomNavItemList
import com.mth.mycomposestarterkit.ui.theme.Black
import com.mth.mycomposestarterkit.ui.theme.Yellow

/**
 * @Author myothiha
 * Created 29/02/2024 at 10:18 PM.
 **/

@Composable
fun BottomNavigationView(
    navController: NavController,
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    AnimatedVisibility(
        visible = shouldShowBottomBar(
            currentDestination = currentDestination,
            navItemList = bottomNavItemList
        ),
        enter = slideInVertically(
            animationSpec = tween(
                durationMillis = 500,
                //easing = LinearEasing
            ),
            initialOffsetY = {it}
        ),
        exit = slideOutVertically(
            animationSpec = tween(
                durationMillis = 500,
                //easing = FastOutSlowInEasing
            ),
            targetOffsetY = {it}
        ),
    ) {
        NavigationBar(
            modifier = Modifier,
            navItemList = bottomNavItemList,
            navDestination = currentDestination,
            navController = navController
        )
    }

}

@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    navItemList: List<BottomNavItem> = listOf(),
    navDestination: NavDestination?,
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalDivider(color = Black, thickness = 1.dp)
        androidx.compose.material3.NavigationBar(
            containerColor = Color.Black,
            modifier = modifier
        ) {
            navItemList.forEach { destination ->
                BottomNavigationBarItem(
                    screen = destination,
                    currentDestination = navDestination,
                    navController = navController
                )
            }

        }
    }
}

@Composable
fun RowScope.BottomNavigationBarItem(
    screen: BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavController
) {
    NavigationBarItem(
        label = {
            Text(
                text = screen.title,
                style = MaterialTheme.typography.labelSmall
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                navController.popBackStack()
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = "Bottom Navigation Icon"
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Yellow,
            unselectedIconColor = Color.White,
            indicatorColor = Color.Transparent,
            selectedTextColor = Yellow,
            unselectedTextColor = Color.White
        )
    )
}

private fun shouldShowBottomBar(
    currentDestination: NavDestination?,
    navItemList: List<BottomNavItem>,
): Boolean {
    return navItemList.any {
        it.route == currentDestination?.route
    }
}

