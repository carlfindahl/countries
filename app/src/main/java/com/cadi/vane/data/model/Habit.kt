package com.cadi.vane.data.model

import kotlinx.serialization.Serializable

@Serializable
sealed class Habit(
    val type: String,
    open val name: String,
) {
    data class TimedHabit(
        val id: UInt,
        override val name: String,
        val interval: HabitInterval,
        val timeSpent: Long,
        val timeToComplete: Long,
    ) : Habit("timed", name)

    data class SetHabit(
        val id: UInt,
        override val name: String,
        val interval: HabitInterval,
        val workout: HabitWorkout
    ) : Habit("set", name)

    data class TaskHabit(
        val id: UInt,
        override val name: String,
        val interval: HabitInterval,
        val completed: Boolean,
    ) : Habit("task", name)
}

@Serializable
data class HabitInterval(
    val count: Int,
    val unit: HabitIntervalUnit
)

@Serializable
enum class HabitIntervalUnit {
    DAY,
    WEEK,
    MONTH
}