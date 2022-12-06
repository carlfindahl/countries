package com.cadi.vane.data.db

import androidx.room.TypeConverter
import com.cadi.vane.data.model.CountryFlags

class CountryConverter {
    @TypeConverter
    fun fromCountryFlags(value: CountryFlags?): String? {
        return value?.png
    }

    @TypeConverter
    fun toCountryFlags(value: String?): CountryFlags? {
        return value?.let { CountryFlags(it) }
    }

    @TypeConverter
    fun fromListOfBorders(value: List<String>?): String? {
        return value?.fold("") { acc: String, s: String -> "${s},${acc}" }
    }

    @TypeConverter
    fun toListOfBorders(value: String?): List<String>? {
        return value?.split(",")?.dropLast(1)
    }
}
