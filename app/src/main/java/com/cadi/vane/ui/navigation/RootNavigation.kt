package com.cadi.vane.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cadi.vane.features.HabitListViewModel
import com.cadi.vane.ui.screens.HomeScreen
import org.koin.androidx.compose.koinViewModel

private const val habitIdArgument = "habitId"

fun NavGraphBuilder.homeScreen(
    onNavigateToHabit: (habit: String) -> Unit
) {
    composable("home/{$habitIdArgument}") {
        val viewModel: HabitListViewModel = koinViewModel()
        val uiState by viewModel.viewState.collectAsState()

        HomeScreen(state = uiState, onClearError = viewModel::clearError)
    }
}