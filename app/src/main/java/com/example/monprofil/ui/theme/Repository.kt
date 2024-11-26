package com.example.monprofil.ui.theme

import javax.inject.Inject

class Repository @Inject constructor(private val tmdbAPI: TmdbAPI) {

    // Fonction pour obtenir les films par mot-clé
    suspend fun getFilmsParMotCle(
        apikey: String,
        motcle: String,
        sortBy: String = "popularity.desc"
    ): TmbdResult {
        return tmdbAPI.getFilmsParMotCle(apikey, motcle, sortBy)
    }

    // Fonction pour obtenir les films tendances
    suspend fun getTrendingMovies(
        apikey: String,
        sortBy: String = "popularity.desc"
    ): TmbdResult {
        return tmdbAPI.getTrendingMovies(apikey, sortBy)
    }

    // Fonction pour obtenir les détails d'un film
    suspend fun getFilmDetails(
        filmId: Int,
        apiKey: String,
        language: String = "fr"
    ): TmbdResult {
        return tmdbAPI.getFilmDetails(filmId, apiKey, language)
    }

    // Fonction pour obtenir les acteurs populaires
    suspend fun getActeur(
        apikey: String,
        language: String = "fr-FR",
        sortBy: String = "popularity.desc"
    ): TmdbActeur {
        return tmdbAPI.getActeur(apikey, language, sortBy)
    }

    // Fonction pour rechercher un acteur par mot-clé
    suspend fun getActeurParMotCle(
        apikey: String,
        motCle: String,
        language: String = "fr-FR",
        sortBy: String = "popularity.desc"
    ): TmdbActeur {
        return tmdbAPI.getActeurParMotCle(apikey, motCle, language, sortBy)
    }

    // Fonction pour découvrir les séries populaires
    suspend fun getDiscoverTV(
        apikey: String,
        language: String = "fr-FR",
        sortBy: String = "popularity.desc"
    ): TmdbSeries {
        return tmdbAPI.getDiscoverTV(apikey, language, sortBy)
    }

    // Fonction pour rechercher des séries par mot-clé
    suspend fun getSeriesParMotCle(
        apikey: String,
        motCle: String,
        language: String = "fr-FR",
        sortBy: String = "popularity.desc"
    ): TmdbSeries {
        return tmdbAPI.getSeriesParMotCle(apikey, motCle, language, sortBy)
    }

    // Fonction pour obtenir les détails d'une série
    suspend fun selectOfSerie(id: Int, apiKey: String): DetailedSerie {
        return tmdbAPI.selectOfSerie(id, apiKey)
    }

    // Fonction pour obtenir les crédits d'une série
    suspend fun acteurseries(id: Int, apiKey: String): SerieCreditsResult {
        return tmdbAPI.acteurseries(id, apiKey)
    }

    // Fonction pour obtenir les détails d'un film sélectionné
    suspend fun selectOfMovie(id: Int, apiKey: String): DetailedMovie {
        return tmdbAPI.selectOfMovie(id, apiKey)
    }

    // Fonction pour obtenir les crédits d'un film
    suspend fun acteurfilm(id: Int, apiKey: String): MovieCreditsResult {
        return tmdbAPI.acteurfilm(id, apiKey)
    }
}
