package com.example.monprofil.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import javax.inject.Inject



class Repository @Inject constructor(
    private val tmdbAPI: TmdbAPI,  // API service pour interagir avec TMDb
    private val filmDao: FilmDao  // DAO pour interagir avec la base de données (Room)
) {

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

    // Ajouter un film aux favoris
    suspend fun addFavoriteFilm(film: Movie) {
        filmDao.insertFilm(
            FilmEntity(
                fiche = film,
                id = film.id
            )
        )  // On ajoute le film aux favoris dans la DB
    }



    // Supprimer un film des favoris
    suspend fun removeFavoriteFilm(filmId: Int) {
        filmDao.deleteFilmById(filmId)  // Suppression du film par ID dans la DB
    }
    // Fonction pour récupérer les films favoris
    fun getFavoriteFilms(): LiveData<List<Movie>> {
        return liveData {
            val favoriteFilms = filmDao.getAllFilms().map { it.fiche }
            emit(favoriteFilms)
        }
    }

    // Fonction pour mettre à jour l'état des favoris dans les résultats
    suspend fun updateFavorites(result: TmbdResult): TmbdResult {
        val favoriteIds = filmDao.getAllFilms().map { it.id }.toSet()
        val updatedResults = result.results.map { movie ->
            if (favoriteIds.contains(movie.id)) {
                movie.copy(isFav = true)
            } else {
                movie
            }
        }
        return result.copy(results = updatedResults)
    }


}

