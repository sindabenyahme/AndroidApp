package com.example.monprofil.ui.theme

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmEntity(
    val fiche: Movie, // Type complexe
    @PrimaryKey val id: Int // Identifiant unique
)
