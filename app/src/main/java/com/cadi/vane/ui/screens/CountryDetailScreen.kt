package com.cadi.vane.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cadi.vane.features.CountryDetailViewModel

@Composable
fun CountryDetailScreen(
    uiState: CountryDetailViewModel.UiState,
    onNavigateToBorderingCountry: (country: String) -> Unit,
) {
    AnimatedVisibility(visible = uiState.country != null) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = uiState.country?.name ?: "Nothing")
        }
    }
}