@file:OptIn(ExperimentalSerializationApi::class)

package com.cadi.vane

import com.cadi.vane.data.HabitNetworkRepository
import com.cadi.vane.data.HabitRepository
import com.cadi.vane.features.HabitListViewModel
import com.cadi.vane.network.HabitsApiService
import com.cadi.vane.network.MockHabitApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit

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

    single<HabitsApiService> { MockHabitApiService() /*get<Retrofit>().create()*/ }
}
