package com.cadi.vane.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cadi.vane.features.HabitListViewModel
import com.cadi.vane.ui.components.ErrorBox
import com.cadi.vane.ui.components.HabitCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    state: HabitListViewModel.ViewState,
    onClearError: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        item {
            AnimatedVisibility(
                visible = state.error != null,
                enter = expandVertically(expandFrom = Alignment.Top) { 0 } + fadeIn(),
                exit = shrinkVertically(shrinkTowards = Alignment.Top) + fadeOut()
            ) {
                ErrorBox(error = state.error ?: "", onDismiss = onClearError)
            }
        }

        item {
            Text(
                text = "Today",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        items(state.habits) {
            HabitCard(habit = it, modifier = Modifier.padding(vertical = 2.dp))
        }
    }
}
