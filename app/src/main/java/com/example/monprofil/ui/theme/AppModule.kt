package com.example.monprofil.ui.theme
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi


import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideConverters(): Converters {
        val moshi = Moshi.Builder().build()
        return Converters(moshi)
    }

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
        converters: Converters
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .addTypeConverter(converters)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideFilmDao(database: AppDatabase): FilmDao {
        return database.filmDao()
    }

    // Fournir l'instance de TmdbAPI via Retrofit
    @Singleton
    @Provides
    fun provideTmdbAPI(): TmdbAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create(TmdbAPI::class.java)
    }

    // Fournir l'instance de Repository
    @Singleton
    @Provides
    fun provideRepository(
        tmdbAPI: TmdbAPI,    // Injection de l'API
        filmDao: FilmDao     // Injection du DAO Film
    ): Repository {
        return Repository(tmdbAPI, filmDao)  // Création et retour de Repository
    }
}

