package com.cadi.vane.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.cadi.vane.data.model.CountryTopBarState
import com.cadi.vane.data.model.LocalAppBarState
import com.cadi.vane.features.CountryListViewModel
import com.cadi.vane.ui.components.CountryCard
import com.cadi.vane.ui.components.ErrorBox

@Composable
fun HomeScreen(
    state: CountryListViewModel.UiState,
    onClickCountry: (String) -> Unit,
    onClearError: () -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    Column {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primaryContainer,
        ) {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                leadingIcon = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Outlined.Search),
                        contentDescription = "Search Icon"
                    )
                }
            )
        }

        AnimatedVisibility(
            visible = state.error != null,
            enter = expandVertically(expandFrom = Alignment.Top) { 0 } + fadeIn(),
            exit = shrinkVertically(shrinkTowards = Alignment.Top) + fadeOut()
        ) {
            ErrorBox(error = state.error ?: "", onDismiss = onClearError)
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            items(state.habits.filter { item ->
                if (searchText.isBlank()) {
                    true
                } else {
                    searchText.lowercase() in item.name.lowercase() || searchText.lowercase() in item.id.lowercase()
                }
            }) {
                CountryCard(
                    country = it,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
                    onClick = { onClickCountry(it.id) }
                )
            }
        }
    }
}
