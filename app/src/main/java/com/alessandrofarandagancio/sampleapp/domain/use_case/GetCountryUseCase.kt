package com.alessandrofarandagancio.sampleapp.domain.use_case

import com.alessandrofarandagancio.sampleapp.common.NetworkHelper
import com.alessandrofarandagancio.sampleapp.domain.Resource
import com.alessandrofarandagancio.sampleapp.domain.model.Country
import com.alessandrofarandagancio.sampleapp.domain.model.toCountry
import com.alessandrofarandagancio.sampleapp.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
import retrofit2.HttpException
import java.io.IOException

class GetCountryUseCase(
    private val repository: CountryRepository,
    private val networkHelper: NetworkHelper
) {

    operator fun invoke(): Flow<Resource<List<Country>>> = flow {
        val networkAvailable = networkHelper.isConnected.take(1).last()
        if (networkAvailable) {
            try {
                val countries = repository.getCountries().map { it.toCountry() }
                emit(Resource.Success(data = countries))
            } catch (http: HttpException) {
                emit(
                    Resource.Error(
                        message = http.localizedMessage ?: "Unexpected error happened."
                    )
                )
            } catch (io: IOException) {
                emit(
                    Resource.Error(
                        message = io.localizedMessage
                            ?: "An error happened! If persist Contact the system administrator."
                    )
                )
            }
        } else {
            emit(
                Resource.Error(
                    message = "Check your connection!"
                )
            )
        }
    }

}