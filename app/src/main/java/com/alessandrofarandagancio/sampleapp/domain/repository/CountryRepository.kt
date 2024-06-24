package com.alessandrofarandagancio.sampleapp.domain.repository

import com.alessandrofarandagancio.sampleapp.data.api.dto.CountryDto

interface CountryRepository {
    suspend fun getCountries(): List<CountryDto>
}