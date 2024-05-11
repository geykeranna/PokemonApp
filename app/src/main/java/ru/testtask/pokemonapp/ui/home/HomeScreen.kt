package ru.testtask.pokemonapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.wear.compose.material.Text
import dagger.hilt.android.EntryPointAccessors
import ru.testtask.pokemonapp.di.navigation.NavigationFactory
import ru.testtask.pokemonapp.di.navigation.NavigationScreenFactory
import ru.testtask.pokemonapp.ui.MainActivity
import ru.testtask.pokemonapp.ui.detail.DetailScreenFactory
import javax.inject.Inject

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val pokemonsList = viewModel.pokemonList.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Покемоны:",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
        )
        LazyColumn {
            items( pokemonsList.itemCount ) {
                pokemonsList[it]?.let { pokemon ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .clickable {
                                   navController.navigate(DetailScreenFactory.route + "/${pokemon.id}")
                            }
                        ,
                        text = pokemon.name,
                    )
                }
            }
        }
    }
}

class HomeScreenFactory @Inject constructor() : NavigationScreenFactory {
    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Nested)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(route = route) {
            HomeScreen(navGraph)
        }
    }
}