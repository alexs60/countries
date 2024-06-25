package com.alessandrofarandagancio.sampleapp.get_countries.datas

import com.alessandrofarandagancio.sampleapp.data.api.dto.CountryDto
import com.alessandrofarandagancio.sampleapp.data.repository.GetCountryError
import com.alessandrofarandagancio.sampleapp.domain.Resource
import com.alessandrofarandagancio.sampleapp.domain.repository.CountryRepository

class TestCountriesRepository(private val list: List<CountryDto>) : CountryRepository {
    override suspend fun getCountries(): Resource<List<CountryDto>, GetCountryError> = Resource.Success(list)
}