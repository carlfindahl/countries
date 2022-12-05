package com.cadi.vane.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.cadi.vane.features.CountryDetailViewModel
import com.google.accompanist.navigation.animation.composable
import com.cadi.vane.features.CountryListViewModel
import com.cadi.vane.ui.screens.CountryDetailScreen
import com.cadi.vane.ui.screens.HomeScreen
import org.koin.androidx.compose.koinViewModel

const val countryIdArgument = "countryCode"

fun NavGraphBuilder.homeScreen(
    onNavigateToCountry: (country: String) -> Unit
) {
    composable("country_home") {
        val viewModel: CountryListViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()

        HomeScreen(
            state = uiState,
            onClearError = viewModel::clearError,
            onClickCountry = onNavigateToCountry
        )
    }
}

fun NavGraphBuilder.countryDetailsScreen(
    onNavigateToCountry: (country: String) -> Unit
) {
    composable("country_details/{$countryIdArgument}") {
        val viewModel: CountryDetailViewModel = koinViewModel()
        val uiState by viewModel.viewState.collectAsState()

        CountryDetailScreen(
            uiState = uiState,
            onNavigateToBorderingCountry = onNavigateToCountry
        )
    }
}

fun NavController.navigateToCountryDetailsScreen(countryId: String) {
    navigate("country_details/$countryId")
}
