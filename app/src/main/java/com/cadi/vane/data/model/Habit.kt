package com.cadi.vane.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class Habit(
    val type: String,
) {
    abstract val name: String

    @Serializable
    @SerialName("timed")
    data class TimedHabit(
        val id: UInt,
        override val name: String,
        val interval: HabitInterval,
        val timeSpent: Long,
        val timeToComplete: Long,
    ) : Habit("timed")

    @Serializable
    @SerialName("set")
    data class SetHabit(
        val id: UInt,
        override val name: String,
        val interval: HabitInterval,
        val workout: HabitWorkout
    ) : Habit("set")

    @Serializable
    @SerialName("task")
    data class TaskHabit(
        val id: UInt,
        override val name: String,
        val interval: HabitInterval,
        val completed: Boolean,
    ) : Habit("task")
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