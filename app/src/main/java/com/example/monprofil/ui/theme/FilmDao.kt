package com.example.monprofil.ui.theme


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FilmDao {

    @Insert
    suspend fun insert(film: Film)

    @Query("SELECT * FROM films")
    suspend fun getAllFilms(): List<Film>

    @Query("SELECT * FROM films WHERE id = :filmId")
    suspend fun getFilmById(filmId: Int): Film?
}
