@file:OptIn(ExperimentalMaterial3Api::class)

package com.cadi.vane

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cadi.vane.features.HabitListViewModel
import com.cadi.vane.ui.components.ErrorBox
import com.cadi.vane.ui.components.HabitCard
import com.cadi.vane.ui.components.VaneTopBar
import com.cadi.vane.ui.theme.VaneTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VaneTheme {
                Scaffold(bottomBar = {
                    BottomAppBar(containerColor = MaterialTheme.colorScheme.secondaryContainer) {
                        VaneNavigation.bottomBarDestinations.forEach { destination ->
                            NavigationBarItem(
                                selected = destination.selected,
                                onClick = { /*TODO*/ },
                                icon = {
                                    Icon(imageVector = destination.getIcon(), null)
                                })
                        }
                    }
                }) {
                    Column(modifier = Modifier.padding(it)) {
                        MainScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: HabitListViewModel = koinViewModel()) {

    val state by viewModel.viewState.collectAsState()

    Column {
        VaneTopBar(name = "Home", message = "Do your thing 3 more times so it's not in Vane!")

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            item {
                AnimatedVisibility(
                    visible = state.error != null,
                    enter = expandVertically(expandFrom = Alignment.Top) { 0 } + fadeIn(),
                    exit = shrinkVertically(shrinkTowards = Alignment.Top) + fadeOut()
                ) {
                    ErrorBox(error = state.error ?: "", onDismiss = { viewModel.clearError() })
                }
            }

            items(state.habits) {
                HabitCard(habit = it, modifier = Modifier.padding(vertical = 2.dp))
            }
        }
    }

}