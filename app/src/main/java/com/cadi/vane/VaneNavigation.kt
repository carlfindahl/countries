package com.cadi.vane

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

object VaneNavigation {
    data class BottomBarDestination(
        val name: String,
        val defaultIcon: ImageVector,
        val selectedIcon: ImageVector,
        val selected: Boolean,
    ) {
        fun getIcon(): ImageVector {
            return if (selected) {
                selectedIcon
            } else {
                defaultIcon
            }
        }
    }

    object Routes {
        const val VANE_LIST = "vanelist"
        const val VANE_INFO = "vaneinfo"
        const val PROFILE = "profile"
    }

    val bottomBarDestinations = listOf(
        BottomBarDestination(
            "Home",
            Icons.Outlined.Home,
            Icons.Filled.Home,
            true
        ),
        BottomBarDestination(
            "Profile",
            Icons.Outlined.Person,
            Icons.Filled.Person,
            false
        ),
    )
}