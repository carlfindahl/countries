package com.cadi.vane.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cadi.vane.data.model.Country


@Database(entities = [Country::class], version = 1)
@TypeConverters(CountryConverter::class)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}
