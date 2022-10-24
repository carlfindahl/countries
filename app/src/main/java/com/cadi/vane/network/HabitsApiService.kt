package com.cadi.vane.network

import com.cadi.vane.data.model.Habit
import com.cadi.vane.data.model.HabitInterval
import com.cadi.vane.data.model.HabitIntervalUnit
import com.cadi.vane.data.model.HabitWorkout
import retrofit2.http.GET

interface HabitsApiService {
    @GET("v1/habits")
    suspend fun getAllHabits(): List<Habit>

    @GET("v1/habits/{habit}")
    suspend fun getHabit(habit: UInt): Habit
}

class MockHabitApiService : HabitsApiService {
    override suspend fun getAllHabits(): List<Habit> {
        return listOf(
            Habit.TaskHabit(0U, "Vakse Gulv", HabitInterval(1, HabitIntervalUnit.WEEK), false),
            Habit.SetHabit(1U, "Pushups", HabitInterval(1, HabitIntervalUnit.DAY), HabitWorkout(0, 3, emptyList())),
            Habit.TimedHabit(2U, "Rydde Hus",HabitInterval(2, HabitIntervalUnit.DAY), 0L, 20L),
        )
    }

    override suspend fun getHabit(habit: UInt): Habit {
        return when (habit) {
            0U ->Habit.TaskHabit(0U, "Vakse Gulv", HabitInterval(1, HabitIntervalUnit.WEEK), false)
            1U -> Habit.SetHabit(1U, "Pushups", HabitInterval(1, HabitIntervalUnit.DAY), HabitWorkout(0, 3, emptyList()))
            2U ->  Habit.TimedHabit(2U, "Rydde Hus",HabitInterval(2, HabitIntervalUnit.DAY), 0L, 20L)
            else -> Habit.TimedHabit(2U, "Rydde Hus",HabitInterval(2, HabitIntervalUnit.DAY), 0L, 20L)
        }
    }
}
