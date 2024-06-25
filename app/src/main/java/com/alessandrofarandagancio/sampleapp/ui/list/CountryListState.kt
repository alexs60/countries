package com.alessandrofarandagancio.sampleapp.ui.list

import com.alessandrofarandagancio.sampleapp.domain.BaseError
import com.alessandrofarandagancio.sampleapp.domain.model.Country
import com.alessandrofarandagancio.sampleapp.domain.use_case.GetCountryError
import java.util.Stack

data class CountryListState(
    val isLoading: Boolean = false,
    val countries: List<Country> = emptyList(),
    val errors: Stack<GetCountryError> = Stack()
)
