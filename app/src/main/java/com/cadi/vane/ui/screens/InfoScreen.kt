package com.cadi.vane.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cadi.vane.R

@Composable
fun InfoScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "APIs", style = MaterialTheme.typography.titleMedium)
        Text("https://restcountries.com", style = MaterialTheme.typography.bodyMedium)
        Text("https://api.apify.com", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Tech", style = MaterialTheme.typography.titleMedium)
        Text("Jetpack Compose, Retrofit, Flow, Coroutines, Coil, Koin, Room", style = MaterialTheme.typography.bodyMedium)

        Image(painter = painterResource(id = R.drawable.good_duck), contentDescription = "Duck",
        modifier = Modifier.size(256.dp))
    }
}
