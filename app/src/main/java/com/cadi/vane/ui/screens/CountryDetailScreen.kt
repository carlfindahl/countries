package com.cadi.vane.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cadi.vane.data.model.Country
import com.cadi.vane.features.CountryDetailViewModel

@Composable
fun CountryDetailScreen(
    uiState: CountryDetailViewModel.UiState,
    onNavigateToBorderingCountry: (country: String) -> Unit,
) {
    val country = uiState.country

    if (country != null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            AsyncImage(
                model = country.flags.png,
                contentDescription = "Flag",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.67f)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                if (country.capital != null) {
                    CountryFactRow(
                        icon = Icons.Filled.Home,
                        fact = "${country.capital} is the Capital"
                    )
                }

                CountryFactRow(icon = Icons.Filled.Person, fact = "${country.population} people")
                CountryFactRow(icon = Icons.Filled.Email, fact = "Referred to as ${country.demonym}")
                CountryFactRow(icon = Icons.Filled.AccountBox, fact = "Borders ${country.borders?.size ?: 0} countries")
            }
        }
    }
}

@Composable
fun CountryFactRow(icon: ImageVector, fact: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        val painter = rememberVectorPainter(image = icon)
        Icon(
            painter = painter,
            contentDescription = "$fact icon",
            tint = MaterialTheme.colorScheme.secondary
        )

        Text(text = fact, style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(start = 16.dp))
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
