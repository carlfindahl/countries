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
import com.cadi.vane.ui.theme.VaneTheme

@Composable
fun HabitCard(
    habit: Habit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = habit.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                )

                Text(
                    text = habit.type,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                HabitButton(
                    symbol = "D",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(2.dp)
                )
                HabitButton(
                    symbol = "H",
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(2.dp)
                )
                HabitButton(
                    symbol = "C",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHabitCard() {
    VaneTheme {
        HabitCard(Habit(0U, "Pushup", "Chore"))
    }
}