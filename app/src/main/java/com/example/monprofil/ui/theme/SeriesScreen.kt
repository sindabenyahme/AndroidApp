package com.example.monprofil.ui.theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun SeriesScreen(navController: NavController, viewModel: MainViewModel, onClick: (id: String) -> Unit) {
    var selectedTab by remember { mutableStateOf("series") }
    var isSearchActive by remember { mutableStateOf(false) } // Contrôle l'affichage du champ de recherche
    var searchText by remember { mutableStateOf("") }
    val series by viewModel.series.collectAsState()

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
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profil") },
                    label = { Text("Profil") },
                    selected = selectedTab == "profil",
                    onClick = {
                        selectedTab = "profil"
                        navController.navigate(ProfilDestination())
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Barre supérieure avec fond coloré
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFADD8E6)) // Couleur uniforme
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (!isSearchActive) {
                    // Affiche "FavAPP" et l'icône de recherche
                    Text(
                        text = "FavAPP",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    IconButton(onClick = { isSearchActive = true }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Recherche",
                            tint = Color.Black
                        )
                    }
                } else {
                    // Affiche le champ de recherche
                    TextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        placeholder = { Text("Rechercher series...") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp),
                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = "Recherche")
                        },
                        trailingIcon = {
                            IconButton(onClick = {
                                isSearchActive = false
                                searchText = "" // Réinitialise la recherche
                            }) {
                                Icon(Icons.Default.Close, contentDescription = "Fermer")
                            }
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                viewModel.searchSeries(searchText)
                            }
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Grille des séries
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                items(series) { serie ->
                    SerieCard(serie = serie, onClick)
                }
            }
        }
    }
}
