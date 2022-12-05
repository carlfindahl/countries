package com.cadi.vane.data

import com.cadi.vane.data.model.Country
import com.cadi.vane.network.CountryApiService
import com.cadi.vane.network.util.NetResult
import com.cadi.vane.network.util.doNetwork

interface CountryRepository {
    suspend fun getAllCountries(): NetResult<List<Country>, String>

    suspend fun getCountry(id: String): NetResult<Country?, String>
}

class CountryNetworkRepository(
    private val habitsApiService: CountryApiService
) : CountryRepository {

    override suspend fun getAllCountries(): NetResult<List<Country>, String> {
        return doNetwork { habitsApiService.getAllCountries() }
    }

    override suspend fun getCountry(id: String): NetResult<Country?, String> {
        return doNetwork { habitsApiService.getCountry(id).firstOrNull() }
    }
}
