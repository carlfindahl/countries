@file:OptIn(ExperimentalMaterial3Api::class)

package com.cadi.vane

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cadi.vane.features.HabitListViewModel
import com.cadi.vane.ui.components.ErrorBox
import com.cadi.vane.ui.components.HabitCard
import com.cadi.vane.ui.components.VaneBottomBar
import com.cadi.vane.ui.components.VaneTopBar
import com.cadi.vane.ui.screens.HomeScreen
import com.cadi.vane.ui.screens.ProfileScreen
import com.cadi.vane.ui.theme.VaneTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VaneTheme {
                val navController = rememberAnimatedNavController()

                Scaffold(
                    topBar = {
                        VaneTopBar(
                            name = "Home",
                            message = "Do your thing 3 more times so it's not in Vane!"
                        )
                    },
                    bottomBar = {
                        VaneBottomBar(containerColor = MaterialTheme.colorScheme.secondaryContainer) {
                            val backStack by navController.currentBackStackEntryAsState()
                            val currentDestination = backStack?.destination

                            VaneNavigation.bottomBarDestinations.forEach { destination ->
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
                        startDestination = VaneNavigation.Routes.VANE_LIST,
                        enterTransition = { fadeIn() },
                        exitTransition = { fadeOut() },
                        modifier = Modifier.padding(it)
                    ) {
                        composable(VaneNavigation.Routes.VANE_LIST) {
                            HomeScreen()
                        }

                        composable(VaneNavigation.Routes.PROFILE) {
                            ProfileScreen()
                        }
                    }
                }
            }
        }
    }
}
