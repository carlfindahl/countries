package com.cadi.vane.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cadi.vane.data.model.Country
import com.cadi.vane.features.CountryDetailViewModel

@Composable
fun CountryDetailScreen(
    uiState: CountryDetailViewModel.UiState,
    onNavigateToBorderingCountry: (country: String) -> Unit,
) {
    val country = uiState.country

    if (country != null) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item { BasicCountryInfoCard(country = country) }


            if (country.borders != null) {
                item {
                    Text(
                        text = "Neighbours",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }

                items(country.borders) {
                    Text(text = it, style = MaterialTheme.typography.bodyMedium)
                }
            } else {
                item {
                    Text(
                        text = "No neighbouring countries!",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun BasicCountryInfoCard(
    country: Country
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${country.name} is a country full of ${country.demonym} people. They have an approximate population of ${country.population} as of now.",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
