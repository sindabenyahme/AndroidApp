package com.example.monprofil.ui.theme



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class Film(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String
)
