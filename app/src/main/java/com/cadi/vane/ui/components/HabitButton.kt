package com.cadi.vane.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cadi.vane.ui.theme.VaneTheme


@Composable
fun HabitButton(
    symbol: String, color: Color, modifier: Modifier = Modifier, onClick: () -> Unit = {}
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = color,
        onClick = onClick,
        modifier = modifier.size(32.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = symbol)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHabitButton() {
    VaneTheme {
        LazyRow {
            items(3) {
                HabitButton(
                    symbol = "+",
                    MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}