package com.cadi.vane.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CovidData(
    val infected: Int?,
    val tested: Int?,
    val recovered: Int?,
    val deceased: Int?,
    val country: String,
)
