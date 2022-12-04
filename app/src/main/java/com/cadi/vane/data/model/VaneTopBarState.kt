package com.cadi.vane.data.model

sealed class VaneTopBarState {
    object Hidden : VaneTopBarState()

    data class BasicSlogan(val title: String, val slogan: String) : VaneTopBarState()
}
