package com.alessandrofarandagancio.sampleapp.app;

import com.alessandrofarandagancio.sampleapp.common.Constants.BASE_COUNTRY_URL
import com.alessandrofarandagancio.sampleapp.data.api.CountryApi
import com.alessandrofarandagancio.sampleapp.data.repository.ApiCountryRepository
import com.alessandrofarandagancio.sampleapp.domain.repository.CountryRepository
import com.alessandrofarandagancio.sampleapp.domain.use_case.GetCountryUseCase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

interface DomainModule {
    val countryApi: CountryApi
    val countryRepository: CountryRepository
    val getCountryUseCase: GetCountryUseCase
}

class DomainModuleImpl : DomainModule {
    override val countryApi: CountryApi by lazy {
        ThisApplication.appModule.retrofit.create(CountryApi::class.java)
    }
    override val countryRepository: CountryRepository by lazy {
        ApiCountryRepository(countryApi, ThisApplication.appModule.networkHelper, Dispatchers.IO)
    }
    override val getCountryUseCase: GetCountryUseCase by lazy {
        GetCountryUseCase(countryRepository)
    }
}
