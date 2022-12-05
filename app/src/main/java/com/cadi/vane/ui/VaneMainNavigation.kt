package com.cadi.vane.ui

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cadi.vane.data.model.CountryTopBarState
import com.cadi.vane.data.model.LocalAppBarState
import com.cadi.vane.data.model.rememberCountryTopBarState
import com.cadi.vane.ui.navigation.BottomBarDestinations
import com.cadi.vane.ui.components.VaneBottomBar
import com.cadi.vane.ui.components.VaneTopBar
import com.cadi.vane.ui.navigation.countryDetailsScreen
import com.cadi.vane.ui.navigation.homeScreen
import com.cadi.vane.ui.navigation.navigateToCountryDetailsScreen
import com.cadi.vane.ui.screens.ProfileScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@Composable
fun VaneMainNavigation(navController: NavHostController) {
    val appBarState = rememberCountryTopBarState()
    CompositionLocalProvider(LocalAppBarState provides appBarState) {

        Scaffold(
            topBar = {
                when (val state = appBarState.state) {

                    is CountryTopBarState.Style.Basic -> VaneTopBar(name = state.title, null)

                    is CountryTopBarState.Style.BasicSlogan -> VaneTopBar(
                        name = state.title,
                        message = state.slogan
                    )

                    CountryTopBarState.Style.Hidden -> {}
                }
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
                startDestination = BottomBarDestinations.Routes.COUNTRY_HOME,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() },
                popEnterTransition = { fadeIn() },
                popExitTransition = { fadeOut() },
                modifier = Modifier.padding(it)
            ) {
                homeScreen { countryId ->
                    navController.navigateToCountryDetailsScreen(countryId)
                }

                countryDetailsScreen { countryId ->
                    navController.navigateToCountryDetailsScreen(
                        countryId
                    )
                }

                composable(BottomBarDestinations.Routes.PROFILE) {
                    appBarState.state = CountryTopBarState.Style.Basic("Profile")
                    ProfileScreen()
                }
            }
        }
    }
}
