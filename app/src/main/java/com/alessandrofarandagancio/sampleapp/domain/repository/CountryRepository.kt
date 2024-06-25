package com.alessandrofarandagancio.sampleapp.domain.repository

import com.alessandrofarandagancio.sampleapp.data.api.dto.CountryDto
import com.alessandrofarandagancio.sampleapp.data.repository.GetCountryError
import com.alessandrofarandagancio.sampleapp.domain.BaseError
import com.alessandrofarandagancio.sampleapp.domain.Resource

interface CountryRepository {
    suspend fun getCountries(): Resource<List<CountryDto>, GetCountryError>
}