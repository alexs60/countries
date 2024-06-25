package com.alessandrofarandagancio.sampleapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.alessandrofarandagancio.sampleapp.app.ThisApplication
import com.alessandrofarandagancio.sampleapp.common.viewModelFactory
import com.alessandrofarandagancio.sampleapp.domain.Resource
import com.alessandrofarandagancio.sampleapp.domain.use_case.GetCountryError
import com.alessandrofarandagancio.sampleapp.domain.use_case.GetCountryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Stack

class CountryViewModel(
    private val getCountryUseCase: GetCountryUseCase
) : ViewModel() {

    private val _uiStateStateFlow: MutableStateFlow<CountryListState> =
        MutableStateFlow(CountryListState())
    val countryListStateStateFlow = _uiStateStateFlow.asStateFlow()

    fun dispatch(intent: Intent) {
        when (intent) {
            is Intent.GetCountry -> getCountry()
        }
    }

    private fun getCountry() {
        viewModelScope.launch {
            _uiStateStateFlow.emit(_uiStateStateFlow.value.copy(isLoading = true))
            getCountryUseCase().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _uiStateStateFlow.emit(CountryListState(countries = result.data))
                    }

                    is Resource.Failure -> {
                        val errors = Stack<GetCountryError>()
                        errors.push(result.error)
                        _uiStateStateFlow.emit(
                            _uiStateStateFlow.value.copy(
                                isLoading = false,
                                errors = errors
                            )
                        )
                    }
                }
            }
        }
    }

    open class Intent {
        class GetCountry : Intent()
    }
}

fun produceViewModelFactory(): ViewModelProvider.Factory =
    viewModelFactory {
        CountryViewModel(ThisApplication.domainModule.getCountryUseCase)
    }

