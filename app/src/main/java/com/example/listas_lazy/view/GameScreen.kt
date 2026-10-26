package com.example.listas_lazy.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listas_lazy.componentes.GameCard
import com.example.listas_lazy.componentes.StudioCard
import com.example.listas_lazy.repository.getAllGames
import com.example.listas_lazy.repository.getGamesByStudio

@Composable
fun GamesScreen(modifier: Modifier = Modifier){

    var studio by remember{
        mutableStateOf("")
    }
    var gamesListByStudio by remember {
        mutableStateOf(getGamesByStudio(""))
    }

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text        = "Meus jogos favoritos",
            fontSize    = 24.sp,
            fontWeight  = FontWeight.Bold
        )

        OutlinedTextField(

            value         = studio,
            singleLine    = true,
            onValueChange = { studio = it},
            modifier      = Modifier.fillMaxWidth(),
            label         = {Text("Nome do estúdio")},
            trailingIcon  = {

                IconButton(onClick = {
                    gamesListByStudio = getGamesByStudio(studio)

                }) {

                    Icon(
                        imageVector        = Icons.Default.Search,
                        contentDescription = "logo do campo de busca"
                    )
                }
            }
        )

        LazyRow() {
            items(gamesListByStudio){ StudioCard(it) }
        }

        LazyColumn() {
            items(gamesListByStudio){ GameCard(it) }
        }
    }
}
