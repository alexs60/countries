package com.alessandrofarandagancio.sampleapp.ui.list

import com.alessandrofarandagancio.sampleapp.data.repository.GetCountryError
import com.alessandrofarandagancio.sampleapp.domain.model.Country
import java.util.Stack

data class CountryListState(
    val isLoading: Boolean = false,
    val countries: List<Country> = emptyList(),
    val errors: Stack<GetCountryError> = Stack()
)
