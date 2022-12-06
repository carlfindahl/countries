package com.cadi.vane.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cadi.vane.data.model.Country
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Query("SELECT * FROM countries ORDER BY name ASC")
    suspend fun getAllCountries(): List<Country>

    @Query("SELECT * FROM countries WHERE name LIKE :name LIMIT 1")
    suspend fun getByName(name: String) : Country?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(countries: List<Country>)
}
