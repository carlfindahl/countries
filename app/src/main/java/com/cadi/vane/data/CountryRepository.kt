package com.cadi.vane.data

import com.cadi.vane.data.model.Habit
import com.cadi.vane.network.HabitsApiService
import com.cadi.vane.network.util.NetResult
import com.cadi.vane.network.util.doNetwork

interface HabitRepository {
    suspend fun getAllHabits(): NetResult<List<Habit>, String>

    suspend fun getHabit(id: UInt): NetResult<Habit, String>
}

class HabitNetworkRepository(
    private val habitsApiService: HabitsApiService
) : HabitRepository {

    override suspend fun getAllHabits(): NetResult<List<Habit>, String> {
        return doNetwork { habitsApiService.getAllHabits() }
    }

    override suspend fun getHabit(id: UInt): NetResult<Habit, String> {
        return doNetwork { habitsApiService.getHabit(id) }
    }
}
