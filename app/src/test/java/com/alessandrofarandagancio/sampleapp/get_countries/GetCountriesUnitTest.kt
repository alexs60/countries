package com.alessandrofarandagancio.sampleapp.get_countries

import com.alessandrofarandagancio.sampleapp.data.api.dto.CountryDto
import com.alessandrofarandagancio.sampleapp.data.api.dto.CurrencyDto
import com.alessandrofarandagancio.sampleapp.data.api.dto.LanguageDto
import com.alessandrofarandagancio.sampleapp.domain.Resource
import com.alessandrofarandagancio.sampleapp.domain.model.toCountry
import com.alessandrofarandagancio.sampleapp.domain.use_case.GetCountryUseCase
import com.alessandrofarandagancio.sampleapp.get_countries.datas.TestCountriesRepository
import com.alessandrofarandagancio.sampleapp.get_countries.datas.TestNetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import kotlin.coroutines.coroutineContext

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GetCountriesUnitTest {

    private lateinit var getCountryUseCase: GetCountryUseCase

    private val countryDto = CountryDto(
        region = "Region",
        code = "Code",
        capital = "Capital",
        flag = "Flag",
        name = "Name",
        currency = CurrencyDto(name = "Currency", code = "CC", symbol = "C$"),
        language = LanguageDto(code = "LL", name = "Language")
    )

    private val countryDto1 = CountryDto(
        region = "Region_1",
        code = "Code_1",
        capital = "Capital_1",
        flag = "Flag_1",
        name = "Name_1",
        currency = CurrencyDto(name = "Currency_1", code = "CC1", symbol = "C$1"),
        language = LanguageDto(code = "LL1", name = "Language_1")
    )

    val list = listOf(countryDto, countryDto1)

    @Before
    fun sutUp() {
        getCountryUseCase = GetCountryUseCase(TestCountriesRepository(list))
    }

    @Test
    fun `Get Countries From Use Case, size`() = runBlocking {
        val result = getCountryUseCase().last()
        assert(result is Resource.Success)

    }

    @Test
    fun `Get Countries From Use Case, mapped content`() = runBlocking {
        val result = getCountryUseCase().last()
        when (result) {
            is Resource.Failure -> assert(false)
            is Resource.Success -> assert(result.data.contains(countryDto.toCountry()))
        }
    }
}