package com.cadi.vane.data.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class CountryTopBarState {
    var topBarState  by mutableStateOf<Style>(Style.Hidden)

    // True for visible, otherwise false
    var bottomBarState by mutableStateOf<Boolean>(true)

    sealed class Style {
        object Hidden : Style()

        data class Basic(val title: String) : Style()

        data class BasicSlogan(val title: String, val slogan: String) : Style()
    }
}

@Composable
fun rememberCountryTopBarState(): CountryTopBarState = remember { CountryTopBarState() }

var LocalAppBarState = compositionLocalOf<CountryTopBarState> { error("No app bar is configured...") }