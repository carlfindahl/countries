@file:OptIn(ExperimentalMaterial3Api::class)

package com.cadi.vane

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cadi.vane.features.HabitListViewModel
import com.cadi.vane.ui.VaneMainNavigation
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
                VaneMainNavigation(navController = navController)
            }
        }
    }
}
