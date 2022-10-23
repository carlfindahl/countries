package com.cadi.vane.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Habit(
    val id: UInt,
    val name: String,
    val type: String,
)
