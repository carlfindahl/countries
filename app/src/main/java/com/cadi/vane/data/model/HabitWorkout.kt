package com.cadi.vane.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HabitWorkout(
    val setCount: Int,
    val targetSetCount: Int,
    val reps: List<HabitSet>
)

@Serializable
data class HabitSet(
    val reps: List<Int>,
    val targetReps: List<Int>
)
