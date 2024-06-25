package com.alessandrofarandagancio.sampleapp.domain.use_case

import com.alessandrofarandagancio.sampleapp.common.NetworkHelper
import com.alessandrofarandagancio.sampleapp.domain.BaseError
import com.alessandrofarandagancio.sampleapp.domain.Resource
import com.alessandrofarandagancio.sampleapp.domain.Resource.Failure
import com.alessandrofarandagancio.sampleapp.domain.Resource.Success
import com.alessandrofarandagancio.sampleapp.domain.model.Country
import com.alessandrofarandagancio.sampleapp.domain.model.toCountry
import com.alessandrofarandagancio.sampleapp.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
import okio.Timeout
import retrofit2.HttpException
import java.io.IOException

class GetCountryUseCase(
    private val repository: CountryRepository,
    private val networkHelper: NetworkHelper
) {

    operator fun invoke(): Flow<Resource<List<Country>, GetCountryError>> = flow {
        val networkAvailable = networkHelper.isConnected.take(1).last()
        if (networkAvailable) {
            try {
                val countries = repository.getCountries().map { it.toCountry() }
                emit(Success<List<Country>, GetCountryError>(data = countries))
            } catch (http: HttpException) {
                emit(Failure(error = GetCountryError.NETWORK_ERROR))
            } catch (io: IOException) {
                emit(Failure(error = GetCountryError.UNEXPECTED_ERROR))
            }
        } else {
            emit(
                Failure(error = GetCountryError.NO_CONNECTION)
            )
        }
    }
}

enum class GetCountryError : BaseError {
    NO_CONNECTION,
    NETWORK_ERROR,
    UNEXPECTED_ERROR
}