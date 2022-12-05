package com.cadi.vane.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cadi.vane.data.model.Country
import com.cadi.vane.data.model.CountryFlags

@Composable
fun CountryCard(
    country: Country,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(80.dp),
            verticalAlignment = CenterVertically
        ) {
            Box {
                AsyncImage(model = country.flags.png, contentDescription = "Flag")
                Text(
                    text = country.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHabitCard() {
    MaterialTheme {
        CountryCard(
            Country(
                id = "NOR",
                name = "Norway",
                demonym = "Norwegian",
                capital = "Oslo",
                population = 500000,
                flags = CountryFlags(""),
                borders = listOf("SWE")
            )
        )
    }
}