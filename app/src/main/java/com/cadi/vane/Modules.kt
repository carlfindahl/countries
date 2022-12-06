@file:OptIn(ExperimentalSerializationApi::class)

package com.cadi.vane

import com.cadi.vane.data.CountryNetworkRepository
import com.cadi.vane.data.CountryRepository
import com.cadi.vane.features.CountryDetailViewModel
import com.cadi.vane.features.CountryListViewModel
import com.cadi.vane.network.CountryApiService
import com.cadi.vane.network.MockCountryApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val habitModule = module {
    single<CountryRepository> { CountryNetworkRepository(habitsApiService = get()) }
    viewModel { CountryListViewModel(get()) }
    viewModel { CountryDetailViewModel(get(), get()) }
}

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {
    singleOf<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://restcountries.com/")
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
                explicitNulls = false
            }.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    single<CountryApiService> { get<Retrofit>().create() }
}
