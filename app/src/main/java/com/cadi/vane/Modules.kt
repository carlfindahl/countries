@file:OptIn(ExperimentalSerializationApi::class)

package com.cadi.vane.dimodules

import com.cadi.vane.features.HabitNetworkRepository
import com.cadi.vane.features.HabitRepository
import com.cadi.vane.features.HabitListViewModel
import com.cadi.vane.network.HabitsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val habitModule = module {
    single<HabitRepository> { HabitNetworkRepository(habitsApiService = get()) }
    viewModel { HabitListViewModel(get()) }
}

val networkModule = module {
    singleOf<Retrofit> {
        Retrofit.Builder()
            .baseUrl("http://127.0.0.1:9090/")
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()
    }

    single<HabitsApiService> { get<Retrofit>().create() }
}
