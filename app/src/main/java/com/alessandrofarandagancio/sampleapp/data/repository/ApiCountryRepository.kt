package com.alessandrofarandagancio.sampleapp.data.repository

import com.alessandrofarandagancio.sampleapp.data.api.CountryApi
import com.alessandrofarandagancio.sampleapp.data.api.dto.CountryDto
import com.alessandrofarandagancio.sampleapp.domain.repository.CountryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ApiCountryRepository(private val api: CountryApi, private val dispatcher: CoroutineDispatcher) : CountryRepository {
    override suspend fun getCountries(): List<CountryDto> {
        return withContext(dispatcher) {
            api.getCountries()
        }
    }
}