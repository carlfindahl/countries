package com.cadi.vane.network

import com.cadi.vane.data.model.Habit
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
            Habit(0U, "Vakse Gulv", "Boolean"),
            Habit(1U, "Pushups", "Sets"),
            Habit(2U, "Rydde Hus", "Timed"),
            Habit(3U, "Runke Litlits", "Count"),
        )
    }

    override suspend fun getHabit(habit: UInt): Habit {
        return when (habit) {
            0U -> Habit(0U, "Vakse Gulv", "Boolean")
            1U -> Habit(1U, "Pushups", "Sets")
            2U -> Habit(2U, "Rydde Hus", "Timed")
            3U -> Habit(3U, "Runke Litlits", "Count")
            else -> Habit(999U, "Fake", "Error")
        }
    }
}
