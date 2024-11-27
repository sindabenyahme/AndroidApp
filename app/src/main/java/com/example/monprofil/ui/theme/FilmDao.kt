package com.example.monprofil.ui.theme

import androidx.room.*

@Dao
interface FilmDao {
    @Query("SELECT * FROM FilmEntity")
    suspend fun getAllFilms(): List<FilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: FilmEntity)


    @Query("DELETE FROM FilmEntity WHERE id = :filmId")
    suspend fun deleteFilmById(filmId: Int)
}

