package com.example.monprofil.ui.theme

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel

class MainViewModel @Inject constructor(
    private val repo: Repository  // Assure-toi d'avoir l'annotation @Inject ici
) : ViewModel() {

    val movies = MutableStateFlow<List<Movie>>(listOf()) // Liste d'objets Movie
    val series = MutableStateFlow<List<Series>>(listOf()) // Liste d'objets Series
    val acteurs = MutableStateFlow<List<Acteurs>>(listOf()) // Liste d'objets Acteurs

    val movies_select = MutableStateFlow<DetailedMovie>(DetailedMovie())
    val series_select = MutableStateFlow<DetailedSerie>(DetailedSerie())

    val movieCast = MutableStateFlow<List<Cast>>(emptyList()) // Liste vide de Cast
    val seriesCast = MutableStateFlow<List<CastSerie>>(emptyList()) // Liste vide de CastSerie

    val showFavoritesOnly = MutableStateFlow(false) // État du commutateur pour afficher uniquement les favoris

    val apikey = "474915450c136f48794281389330d269"

    // Initialisation des appels
    init {
        getMovies()
        getTrendingSeries()
        getActeurs()
    }

    // Récupérer les films tendances
    private fun getMovies() {
        viewModelScope.launch {
            try {
                val trendingMovies = repo.getTrendingMovies(apikey)
                movies.value = trendingMovies.results
            } catch (e: Exception) {
                Log.e("MainViewModel", "Erreur lors de la récupération des films: ${e.message}")
            }
        }
    }

    // Recherche de films par mot-clé
    fun searchMovies(motcle: String) {
        viewModelScope.launch {
            try {
                val searchResult = repo.getFilmsParMotCle(apikey, motcle)
                movies.value = searchResult.results
            } catch (e: Exception) {
                Log.e("MainViewModel", "Erreur lors de la recherche de films: ${e.message}")
            }
        }
    }

    // Récupérer les séries tendances
    fun getTrendingSeries() {
        viewModelScope.launch {
            try {
                val trendingSeries = repo.getDiscoverTV(apikey)
                series.value = trendingSeries.results
            } catch (e: Exception) {
                Log.e("MainViewModel", "Erreur lors de la récupération des séries: ${e.message}")
            }
        }
    }

    // Recherche de séries par mot-clé
    fun searchSeries(motcle: String) {
        viewModelScope.launch {
            try {
                val searchResult = repo.getSeriesParMotCle(apikey, motcle)
                series.value = searchResult.results
            } catch (e: Exception) {
                Log.e("MainViewModel", "Erreur lors de la recherche de séries: ${e.message}")
            }
        }
    }

    // Récupérer les acteurs tendances
    fun getActeurs() {
        viewModelScope.launch {
            try {
                val trendingActeurs = repo.getActeur(apikey)
                acteurs.value = trendingActeurs.results
            } catch (e: Exception) {
                Log.e("MainViewModel", "Erreur lors de la récupération des acteurs: ${e.message}")
            }
        }
    }

    // Recherche d'acteurs par mot-clé
    fun searchActeurs(motcle: String) {
        viewModelScope.launch {
            try {
                val searchResult = repo.getActeurParMotCle(apikey, motcle)
                acteurs.value = searchResult.results
            } catch (e: Exception) {
                Log.e("MainViewModel", "Erreur lors de la recherche d'acteurs: ${e.message}")
            }
        }
    }

    // Pour sélectionner des films ou séries
    fun selectedMovies(id: Int) {
        viewModelScope.launch {
            movies_select.value = repo.selectOfMovie(id, apikey)
        }
    }

    fun selectedSeries(id: Int) {
        viewModelScope.launch {
            series_select.value = repo.selectOfSerie(id, apikey)
        }
    }

    // Pour afficher les acteurs d'une série ou d'un film sélectionné
    fun getActeurMovie(id: Int) {
        viewModelScope.launch {
            try {
                val result = repo.acteurfilm(id, apikey)
                movieCast.value = result.cast
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getActeurSeries(id: Int) {
        viewModelScope.launch {
            try {
                val serieact = repo.acteurseries(id, apikey)
                seriesCast.value = serieact.cast
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun toggleFavoriteFilm(film: Movie) {
        viewModelScope.launch {
            try {
                val updatedFilm = if (film.isFav) {
                    repo.removeFavoriteFilm(film.id)  // Passe l'ID du film
                    film.copy(isFav = false)
                } else {
                    repo.addFavoriteFilm(film)
                    film.copy(isFav = true)
                }
                updateMoviesList(updatedFilm)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Erreur lors du changement de favori pour le film: ${e.message}")
            }
        }
    }







    // Mise à jour de la liste des films après avoir changé l'état du favori
    private fun updateMoviesList(updatedFilm: Movie) {
        movies.value = movies.value.map { film ->
            if (film.id == updatedFilm.id) updatedFilm else film
        }
    }

    // Met à jour la liste générale en fonction de l'état du favori général
    fun updateFavoriteState() {
        viewModelScope.launch {
            if (showFavoritesOnly.value) {
                // Filtrer les films et séries favoris uniquement
                movies.value = movies.value.filter { it.isFav }

            } else {
                // Récupérer tous les films, séries et acteurs
                getMovies()
                getTrendingSeries()
                getActeurs()
            }
        }
    }
}

