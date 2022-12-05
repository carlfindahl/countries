package com.cadi.vane.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Column {
        AnimatedVisibility(
            visible = state.error != null,
            enter = expandVertically(expandFrom = Alignment.Top) { 0 } + fadeIn(),
            exit = shrinkVertically(shrinkTowards = Alignment.Top) + fadeOut()
        ) {
            ErrorBox(error = state.error ?: "", onDismiss = onClearError)
        }

        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(horizontal = 8.dp)) {
            items(state.habits) {
                CountryCard(
                    country = it,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
                    onClick = { onClickCountry(it.id) }
                )
            }
        }
    }
}
