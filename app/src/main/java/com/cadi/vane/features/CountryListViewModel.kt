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

    private var _viewState = MutableStateFlow(ViewState())
    val viewState = _viewState

    init {
        getAllCountries()
    }

    private fun getAllCountries() = viewModelScope.launch {
        countryRepository.getAllCountries()
            .doOnSuccess {
                _viewState.value =
                    _viewState.value.copy(habits = it)
            }.doOnFailure {
                _viewState.value = _viewState.value.copy(error = it)
            }
    }

    fun clearError() {
        _viewState.value = _viewState.value.copy(error = null)
    }

    data class ViewState(
        val habits: List<Country> = emptyList(),
        val error: String? = null
    )
}
