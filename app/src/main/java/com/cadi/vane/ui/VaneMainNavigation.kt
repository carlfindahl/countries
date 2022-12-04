package com.cadi.vane.ui

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cadi.vane.ui.navigation.BottomBarDestinations
import com.cadi.vane.ui.components.VaneBottomBar
import com.cadi.vane.ui.components.VaneTopBar
import com.cadi.vane.ui.navigation.homeScreen
import com.cadi.vane.ui.screens.ProfileScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@Composable
fun VaneMainNavigation(navController: NavHostController) {

    Scaffold(
        topBar = {
            val backStack by navController.currentBackStackEntryAsState()
            val title = backStack?.destination?.displayName ?: "Vane"

            VaneTopBar(
                name = title,
                message = "Do your thing 3 more times so it's not in Vane!"
            )
        },
        bottomBar = {
            VaneBottomBar(containerColor = MaterialTheme.colorScheme.secondaryContainer) {
                val backStack by navController.currentBackStackEntryAsState()
                val currentDestination = backStack?.destination

                BottomBarDestinations.bottomBarDestinations.forEach { destination ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == destination.name } == true

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(destination.name) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(imageVector = destination.getIcon(selected), null)
                        })
                }
            }
        }) {
        AnimatedNavHost(
            navController,
            startDestination = BottomBarDestinations.Routes.VANE_LIST,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            modifier = Modifier.padding(it)
        ) {
            homeScreen {  }

            composable(BottomBarDestinations.Routes.PROFILE) {
                ProfileScreen()
            }
        }
    }
}
