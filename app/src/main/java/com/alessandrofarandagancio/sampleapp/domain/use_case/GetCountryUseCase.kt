package com.alessandrofarandagancio.sampleapp.domain.use_case

import com.alessandrofarandagancio.sampleapp.data.repository.GetCountryError
import com.alessandrofarandagancio.sampleapp.domain.Resource
import com.alessandrofarandagancio.sampleapp.domain.Resource.Failure
import com.alessandrofarandagancio.sampleapp.domain.Resource.Success
import com.alessandrofarandagancio.sampleapp.domain.model.Country
import com.alessandrofarandagancio.sampleapp.domain.model.toCountry
import com.alessandrofarandagancio.sampleapp.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCountryUseCase(
    private val repository: CountryRepository
) {

    operator fun invoke(): Flow<Resource<List<Country>, GetCountryError>> = flow {
        try {
            when (val result = repository.getCountries()) {
                is Failure -> {
                    emit(Failure(error = result.error))
                }
                is Success -> {
                    val data = result.data.map { it.toCountry() }
                    emit(Success(data = data))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Failure(error = GetCountryError.UNEXPECTED_ERROR))
        }
    }
}
