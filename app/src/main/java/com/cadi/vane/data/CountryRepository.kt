package com.cadi.vane.data

import com.cadi.vane.data.db.CountryDatabase
import com.cadi.vane.data.model.Country
import com.cadi.vane.network.CountryApiService
import com.cadi.vane.network.util.NetResult
import com.cadi.vane.network.util.doNetwork
import com.cadi.vane.network.util.doOnFailure
import com.cadi.vane.network.util.doOnSuccess
import com.cadi.vane.network.util.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface CountryRepository {
    suspend fun getAllCountries(): NetResult<List<Country>, String>

    suspend fun getCountry(id: String): NetResult<Country?, String>
}

class CountryNetworkRepository(
    private val habitsApiService: CountryApiService,
    private val countryDatabase: CountryDatabase,
) : CountryRepository {

    override suspend fun getAllCountries(): NetResult<List<Country>, String> {
        val fromCache = countryDatabase.countryDao().getAllCountries()

        // Refresh if not in cache
        return if (fromCache.isEmpty()) {
            refreshCountriesFromNet()
        } else {
            NetResult.Success(fromCache)
        }
    }

    override suspend fun getCountry(id: String): NetResult<Country?, String> {
        val fromCache = countryDatabase.countryDao().getById(id)

        return if (fromCache == null) {
            refreshCountriesFromNet().map { countries ->
                countries.firstOrNull {
                    it.id == id
                }
            }
        } else {
            NetResult.Success(fromCache)
        }
    }

    private suspend fun refreshCountriesFromNet() =
        doNetwork { habitsApiService.getAllCountries() }.doOnSuccess {
            countryDatabase.countryDao().insertAll(it)
        }
}
