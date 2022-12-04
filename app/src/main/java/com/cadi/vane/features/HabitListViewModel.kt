package com.cadi.vane.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cadi.vane.data.HabitRepository
import com.cadi.vane.data.model.Habit
import com.cadi.vane.network.util.doOnFailure
import com.cadi.vane.network.util.doOnSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HabitListViewModel(private val habitRepository: HabitRepository) : ViewModel() {

    private var _viewState = MutableStateFlow(ViewState())
    val viewState = _viewState

    init {
        getAllHabits()
    }

    private fun getAllHabits() = viewModelScope.launch {
        habitRepository.getAllHabits()
            .doOnSuccess {
                _viewState.value =
                    _viewState.value.copy(habits = it
                        .map { entry -> HabitListItem.HabitEntry(entry) }
                        .groupBy { element -> element.habit.type }
                        .flatMap { category ->
                            listOf(HabitListItem.HabitCategory(category.key)) + category.value
                        })
            }.doOnFailure {
                _viewState.value = _viewState.value.copy(error = it)
            }
    }

    fun clearError() {
        _viewState.value = _viewState.value.copy(error = null)
    }

    data class ViewState(
        val habits: List<HabitListItem> = emptyList(),
        val error: String? = null
    )
}

sealed class HabitListItem {
    data class HabitCategory(val title: String) : HabitListItem()
    data class HabitEntry(val habit: Habit) : HabitListItem()
}
