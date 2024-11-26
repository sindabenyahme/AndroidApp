package com.example.monprofil.ui.theme
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {
    @GET("search/movie")
    suspend
    fun getFilmsParMotCle(
        @Query("api_key") apikey: String,
        @Query("query") motcle: String,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): TmbdResult

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("api_key") apikey: String,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): TmbdResult


    @GET("movie/{id}")
    suspend fun getFilmDetails(
        @Path("id") filmId: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String = "fr"
    ): TmbdResult

    @GET("person/popular")
    suspend fun getActeur(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "fr-FR",
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): TmdbActeur


    @GET("search/person")
    suspend fun getActeurParMotCle(
        @Query("api_key") apiKey: String,
        @Query("query") motCle: String,
        @Query("language") language: String = "fr-FR",
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): TmdbActeur

    @GET("discover/tv")
    suspend fun getDiscoverTV(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "fr-FR",
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): TmdbSeries


    @GET("search/tv")
    suspend fun getSeriesParMotCle(
        @Query("api_key") apiKey: String,
        @Query("query") motCle: String,
        @Query("language") language: String = "fr-FR",
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): TmdbSeries

    @GET("tv/{id}")
    suspend fun selectOfSerie(@Path("id") id: Int, @Query("api_key") api_key: String): DetailedSerie
    @GET("tv/{id}/credits")
    suspend fun acteurseries(@Path("id") id: Int, @Query("api_key") api_Key: String): SerieCreditsResult

    @GET("movie/{id}")
    suspend fun selectOfMovie(@Path("id") id: Int, @Query("api_key") api_key: String): DetailedMovie
    @GET("movie/{id}/credits")
    suspend fun acteurfilm(@Path("id") id: Int, @Query("api_key") api_Key: String): MovieCreditsResult
}
