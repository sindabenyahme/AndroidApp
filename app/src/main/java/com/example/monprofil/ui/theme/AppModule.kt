package com.example.monprofil.ui.theme
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideTmdbApi(): TmdbAPI =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(TmdbAPI::class.java)


    @Provides
    fun provideRepository(
        tmdbAPI: TmdbAPI
    ): Repository = Repository(tmdbAPI)

}
