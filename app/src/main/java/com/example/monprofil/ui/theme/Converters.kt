package com.example.monprofil.ui.theme

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi

import androidx.room.ProvidedTypeConverter

@ProvidedTypeConverter
class Converters(moshi: Moshi) {
    private val filmJsonAdapter = moshi.adapter(Movie::class.java)

    @TypeConverter
    fun StringToTmdbMovie(value: String): Movie? {
        return filmJsonAdapter.fromJson(value)
    }

    @TypeConverter
    fun TmdbMovieToString(film: Movie): String {
        return filmJsonAdapter.toJson(film)
    }
}

