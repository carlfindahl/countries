package com.cadi.vane.network

import com.cadi.vane.data.model.Country
import com.cadi.vane.data.model.CountryFlags
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApiService {
    @GET("v2/all")
    suspend fun getAllCountries(): List<Country>

    @GET("v2/name/{name}")
    suspend fun getCountry(@Path("name") name: String): List<Country>
}

/**
 * Mock class for testing and not wasting API rate limits
 */
class MockCountryApiService : CountryApiService {
    override suspend fun getAllCountries(): List<Country> {
        return listOf(
            Country(
                "NOR",
                "Norway",
                "Norwegian",
                "Oslo",
                500000,
                CountryFlags("https://flagcdn.com/w320/as.png"),
                listOf("SWE")
            ),
            Country(
                "SWE",
                "Sweden",
                "Swedish",
                "Stockholm",
                500000,
                CountryFlags("https://flagcdn.com/w320/as.png"),
                listOf("NOR")
            )
        )
    }

    override suspend fun getCountry(name: String): List<Country> {
        return if (name == "NOR") {
            listOf(
                Country(
                    "NOR",
                    "Norway",
                    "Norwegian",
                    "Oslo",
                    500000,
                    CountryFlags("https://flagcdn.com/w320/as.png"),
                    listOf("SWE")
                )
            )
        } else {
            listOf(
                Country(
                    "SWE",
                    "Sweden",
                    "Swedish",
                    "Stockholm",
                    500000,
                    CountryFlags("https://flagcdn.com/w320/as.png"),
                    listOf("NOR")
                )
            )
        }
    }
}
