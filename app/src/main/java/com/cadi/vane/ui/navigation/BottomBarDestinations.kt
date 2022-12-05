package com.cadi.vane.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
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
        const val COUNTRY_ABOUT = "country_about"
        const val PROFILE = "profile"
    }

    val bottomBarDestinations = listOf(
        BottomBarDestination(
            Routes.COUNTRY_HOME,
            Icons.Outlined.Home,
            Icons.Filled.Home,
        ),
        BottomBarDestination(
            Routes.PROFILE,
            Icons.Outlined.Person,
            Icons.Filled.Person,
        ),
    )
}