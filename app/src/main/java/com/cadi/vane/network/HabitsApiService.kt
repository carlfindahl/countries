package com.cadi.vane.network

import com.cadi.vane.data.model.Habit
import retrofit2.http.GET


interface HabitsApiService {
    @GET("v1/habits")
    suspend fun getAllHabits(): List<Habit>

    @GET("v1/habits/{habit}")
    suspend fun getHabit(habit: UInt): Habit
}