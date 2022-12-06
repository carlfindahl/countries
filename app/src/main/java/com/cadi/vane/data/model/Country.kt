package com.cadi.vane.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "countries")
data class Country(
    @SerialName("alpha3Code") @PrimaryKey val id: String,
    val name: String,
    val demonym: String,
    val subregion: String,
    val capital: String?,
    val population: Int,
    val flags: CountryFlags,
    val borders: List<String>?
)

@Serializable
data class CountryFlags(val png: String)
