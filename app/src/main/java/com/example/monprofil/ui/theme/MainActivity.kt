package com.example.monprofil.ui.theme

import ScreenFilmsDetails
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.serialization.Serializable

@Serializable class ProfilDestination()
@Serializable class FilmsDestination()
@Serializable class SeriesDestination()
@Serializable class ActeursDestination()
@Serializable class FilmDetailDestination(val id: String)
@Serializable class SerieDetailsDestination(val id: String)




@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonProfilTheme {
                val navController = rememberNavController()
                val mainViewModel: MainViewModel = viewModel()

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = ProfilDestination(),
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<ProfilDestination> { ProfilScreen(navController) }
                        composable<FilmsDestination> {
                            FilmScreen(navController, mainViewModel) { filmId ->
                                navController.navigate(FilmDetailDestination(filmId))
                            }
                        }
                        composable<SeriesDestination> {
                            SeriesScreen(navController, mainViewModel) { serieId ->
                            navController.navigate(SerieDetailsDestination(serieId))
                        }}
                        composable<ActeursDestination> { ActeurScreen(navController, mainViewModel) }

                        composable<FilmDetailDestination> { backStackEntry ->
                            val filmDetail: FilmDetailDestination = backStackEntry.toRoute()
                            ScreenFilmsDetails(
                                navController = navController,
                                viewModel = mainViewModel,
                                id = filmDetail.id
                                // Si vous avez besoin de windowClass, vous pouvez l'obtenir ici ou le rendre optionnel
                            )
                        }
                        composable<SerieDetailsDestination> { backStackEntry ->
                            val serieDetail: SerieDetailsDestination = backStackEntry.toRoute()
                            ScreenSerieDetails(
                                navController = navController,
                                viewModel = mainViewModel,
                                id = serieDetail.id
                                // Si vous avez besoin de windowClass, vous pouvez l'obtenir ici ou le rendre optionnel
                            )
                        }




                    }
                }
            }
        }
    }
}