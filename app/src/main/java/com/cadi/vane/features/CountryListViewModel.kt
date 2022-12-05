package com.cadi.vane.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cadi.vane.data.CountryRepository
import com.cadi.vane.data.model.Country
import com.cadi.vane.network.util.doOnFailure
import com.cadi.vane.network.util.doOnSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CountryListViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    private var _uiState = MutableStateFlow(UiState())
    val uiState = _uiState

    init {
        getAllCountries()
    }

    private fun getAllCountries() = viewModelScope.launch {
        countryRepository.getAllCountries()
            .doOnSuccess {
                _uiState.value =
                    _uiState.value.copy(habits = it)
            }.doOnFailure {
                _uiState.value = _uiState.value.copy(error = it)
            }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    data class UiState(
        val habits: List<Country> = emptyList(),
        val error: String? = null
    )
}
