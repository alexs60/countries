package com.alessandrofarandagancio.sampleapp.data.api.dto

data class CountryDto(
    val capital: String,
    val code: String,
    val currency: CurrencyDto,
    val flag: String,
    val language: LanguageDto,
    val name: String,
    val region: String
)

data class CurrencyDto(
    val code: String,
    val name: String,
    val symbol: String
)

data class LanguageDto(
    val code: String,
    val name: String
)