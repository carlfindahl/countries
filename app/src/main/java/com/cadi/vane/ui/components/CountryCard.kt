package com.cadi.vane.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cadi.vane.data.model.Country
import com.cadi.vane.data.model.CountryFlags

@Composable
fun CountryCard(
    country: Country,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val gradient = Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.9f)))

    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier,
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier.fillMaxSize().aspectRatio(1.67f),
            contentAlignment = Alignment.BottomCenter
        ) {

            AsyncImage(
                model = country.flags.png,
                contentDescription = "Flag",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(gradient),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = country.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
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
            ),
            onClick = {}
        )
    }
}