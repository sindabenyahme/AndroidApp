package com.example.monprofil.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.myapplicationtest.playlistjson




@Composable
fun PlayListScreen(navController: NavController, viewModel: MainViewModel) {
                var selectedTab by remember { mutableStateOf("palylist") } // Onglet par défaut
                val playlist by viewModel.playlist.collectAsState()

                Scaffold(
                    bottomBar = {
                        NavigationBar(containerColor = Color(0xFFADD8E6)) {
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Movie, contentDescription = "Film") },
                                label = { Text("Film") },
                                selected = selectedTab == "film",
                                onClick = {
                                    selectedTab = "film"
                                    navController.navigate(FilmsDestination())
                                }
                            )
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Tv, contentDescription = "Series") },
                                label = { Text("Series") },
                                selected = selectedTab == "series",
                                onClick = {
                                    selectedTab = "series"
                                    navController.navigate(SeriesDestination())
                                }
                            )
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Star, contentDescription = "Acteur") },
                                label = { Text("Acteur") },
                                selected = selectedTab == "acteur",
                                onClick = {
                                    selectedTab = "acteur"
                                    navController.navigate(ActeursDestination())
                                }
                            )
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.PlayCircleOutline, contentDescription = "Playlist") },
                                label = { Text("Playlist") },
                                selected = selectedTab == "Playlist",
                                onClick = {
                                    selectedTab = "Playlist"
                                    navController.navigate(PlayListDestination())
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Collection Screen",
                            style = MaterialTheme.typography.headlineSmall
                        )



                        LazyColumn {
                            items(playlist) { playlist ->
                                // Conteneur pour chaque collection
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp)
                                ) {
                                   AsyncImage(
                                       model ="file:///android-asset/images/2.png",
                                       contentDescription=""
                                   )

                                    // Nom de la collection centré
                                    Text(
                                        text = playlist.titre,
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
}



