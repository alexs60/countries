package com.alessandrofarandagancio.sampleapp.domain.model

import com.alessandrofarandagancio.sampleapp.ui.utils.recycler_view.ItemizableModel
import com.alessandrofarandagancio.sampleapp.data.api.dto.CountryDto

data class Country(
    val capital: String,
    val code: String,
    val name: String,
    val region: String,
    override val id: String
) : ItemizableModel

fun CountryDto.toCountry(): Country = Country(
    name = name,
    region = region,
    capital = capital,
    code = code,
    id = code
)