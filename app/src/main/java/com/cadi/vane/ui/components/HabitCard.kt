package com.cadi.vane.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cadi.vane.data.model.Habit
import com.cadi.vane.data.model.HabitInterval
import com.cadi.vane.data.model.HabitIntervalUnit
import com.cadi.vane.ui.theme.VaneTheme

@Composable
fun HabitCard(
    habit: Habit,
    modifier: Modifier = Modifier,
    endContent: @Composable () -> Unit = {}
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
                .fillMaxSize(),
            verticalAlignment = CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = habit.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                )

                Text(
                    text = habit.type,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            endContent()
        }
    }
}

@Preview
@Composable
fun PreviewHabitCard() {
    MaterialTheme {
        HabitCard(
            Habit.TimedHabit(
                2U,
                "Rydde Hus",
                HabitInterval(2, HabitIntervalUnit.DAY),
                0L,
                20L
            )
        )
    }
}