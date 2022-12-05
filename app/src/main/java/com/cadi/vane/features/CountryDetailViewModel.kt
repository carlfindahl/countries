package com.cadi.vane.features

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cadi.vane.data.CountryRepository
import com.cadi.vane.data.model.Country
import com.cadi.vane.network.util.doOnSuccess
import com.cadi.vane.ui.navigation.countryIdArgument
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CountryDetailViewModel(
    savedStateHandle: SavedStateHandle,
    countryRepository: CountryRepository,
) : ViewModel() {

    private val countryId: String = requireNotNull(savedStateHandle[countryIdArgument])

    private var _viewState = MutableStateFlow(UiState())
    val viewState = _viewState

    init {
        viewModelScope.launch {
            countryRepository.getCountry(countryId).doOnSuccess {
                _viewState.value = UiState(country = it)
            }
        }
    }

    data class UiState(
        val country: Country? = null,
    )
}
