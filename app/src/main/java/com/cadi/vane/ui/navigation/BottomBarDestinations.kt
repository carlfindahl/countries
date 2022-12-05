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
        const val VANE_LIST = "home"
        const val VANE_INFO = "vaneinfo"
        const val PROFILE = "profile"
    }

    val bottomBarDestinations = listOf(
        BottomBarDestination(
            Routes.VANE_LIST,
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