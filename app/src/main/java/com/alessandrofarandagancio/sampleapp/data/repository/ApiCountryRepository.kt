package com.alessandrofarandagancio.sampleapp.data.repository

import com.alessandrofarandagancio.sampleapp.common.NetworkHelper
import com.alessandrofarandagancio.sampleapp.data.api.CountryApi
import com.alessandrofarandagancio.sampleapp.data.api.dto.CountryDto
import com.alessandrofarandagancio.sampleapp.domain.BaseError
import com.alessandrofarandagancio.sampleapp.domain.Resource
import com.alessandrofarandagancio.sampleapp.domain.repository.CountryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class ApiCountryRepository(
    private val api: CountryApi,
    private val networkHelper: NetworkHelper,
    private val dispatcher: CoroutineDispatcher
) : CountryRepository {
    override suspend fun getCountries(): Resource<List<CountryDto>, GetCountryError> {
        return withContext(dispatcher) {
            api.getCountries()

            val networkAvailable = networkHelper.isConnected.take(1).last()
            if (networkAvailable) {
                try {
                    val countries = api.getCountries()
                    Resource.Success(data = countries)
                } catch (http: HttpException) {
                    // here may we want to handle HTTP codes
                    Resource.Failure(error = GetCountryError.NETWORK_ERROR)
                } catch (io: IOException) {
                    Resource.Failure(error = GetCountryError.UNEXPECTED_ERROR)
                } catch (socket: SocketTimeoutException) {
                    Resource.Failure(error = GetCountryError.TIMEOUT)
                } catch (e: Exception) {
                    Resource.Failure(error = GetCountryError.UNEXPECTED_ERROR)
                }
            } else {
                Resource.Failure(error = GetCountryError.NO_CONNECTION)
            }
        }
    }
}

enum class GetCountryError : BaseError {
    NO_CONNECTION,
    NETWORK_ERROR,
    UNEXPECTED_ERROR,
    TIMEOUT
}