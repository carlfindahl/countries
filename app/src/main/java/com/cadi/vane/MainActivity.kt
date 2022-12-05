@file:OptIn(ExperimentalMaterial3Api::class)

package com.cadi.vane

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import com.cadi.vane.ui.VaneMainNavigation
import com.cadi.vane.ui.theme.VaneTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

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
