package com.cadi.vane.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.ui.graphics.vector.ImageVector

object BottomBarDestinations {
    data class BottomBarDestination(
        val name: String,
        val defaultIcon: ImageVector,
        val selectedIcon: ImageVector,
    ) {
        fun getIcon(selected: Boolean): ImageVector {
            return if (selected) {
                selectedIcon
            } else {
                defaultIcon
            }
        }
    }

    object Routes {
        const val COUNTRY_HOME = "country_home"
        const val INFO = "info"
    }

    val bottomBarDestinations = listOf(
        BottomBarDestination(
            Routes.COUNTRY_HOME,
            Icons.Outlined.Home,
            Icons.Filled.Home,
        ),
        BottomBarDestination(
            Routes.INFO,
            Icons.Outlined.Info,
            Icons.Filled.Info,
        ),
    )
}