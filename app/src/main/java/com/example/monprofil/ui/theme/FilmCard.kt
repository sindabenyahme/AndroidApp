package com.example.monprofil.ui.theme
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmCard(movie: Movie, onClick: (id: String) -> Unit, onFavoriteToggle: (Movie) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick(movie.id.toString()) }, // Pass the ID as a string
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color(0xFF4682B4)) // Steel blue
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            // Movie Poster
            val imageUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
            AsyncImage(
                model = imageUrl,
                contentDescription = movie.title,
                modifier = Modifier
                    .size(180.dp)
                    .padding(bottom = 12.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .border(2.dp, Color.White, MaterialTheme.shapes.medium)
            )

            // Movie Title and Release Date
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    text = movie.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 1,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Release: ${movie.release_date}",
                    fontSize = 16.sp,
                    color = Color.LightGray
                )
            }

            // Favorite Toggle Button
            IconButton(
                onClick = { onFavoriteToggle(movie) },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(
                    imageVector = if (movie.isFav) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (movie.isFav) Color.Red else Color.White
                )
            }
        }
    }
}
