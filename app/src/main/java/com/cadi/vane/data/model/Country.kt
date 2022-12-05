package com.cadi.vane.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Country(
    @SerialName("alpha3Code") val id: String,
    val name: String,
    val demonym: String,
    val capital: String,
    val population: Int,
    val flags: CountryFlags,
    val borders: List<String>
)

@Serializable
data class CountryFlags(val png: String)

/*
Placidusax
 */