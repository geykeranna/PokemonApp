package ru.testtask.pokemonapp.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.wear.compose.material.Text
import ru.testtask.pokemonapp.domain.model.Pokemons
import ru.testtask.pokemonapp.ui.components.EmptyScreen
import ru.testtask.pokemonapp.ui.components.LoadingAnimation
import ru.testtask.pokemonapp.ui.detail.DetailScreenFactory

@Composable
fun PokemonList(
    pokemons: LazyPagingItems<Pokemons>,
    navController: NavController
) {
    if (handlePagingResults(pokemons)) {
        LazyColumn {
            items( pokemons.itemCount ) {
                pokemons[it]?.let { pokemon ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .clickable {
                                navController.navigate(DetailScreenFactory.route + "/${pokemon.id}")
                            }
                        ,
                        text = pokemon.name,
                        fontSize = 26.sp,
                        color = MaterialTheme.typography.bodyLarge.color
                    )
                }
            }
        }
    }

}


@Composable
fun handlePagingResults(
    pokemonsList: LazyPagingItems<Pokemons>,
): Boolean {
    val loadState = pokemonsList.loadState
    val error = when{
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            LoadingAnimation(
                modifier = Modifier.fillMaxSize()
            )
            false
        }
        pokemonsList.itemCount == 0 -> {
            EmptyScreen(isEmpty = true)
            false
        }
        error != null -> {
            EmptyScreen()
            false
        }
        else -> {
            true
        }
    }
}