package ru.testtask.pokemonapp.ui.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dagger.hilt.android.EntryPointAccessors
import ru.testtask.pokemonapp.ui.MainActivity
import ru.testtask.pokemonapp.di.navigation.NavigationFactory
import ru.testtask.pokemonapp.di.navigation.NavigationScreenFactory
import javax.inject.Inject

@Composable
fun DetailScreen(
    viewModel: DetailViewModel
) {

}

@Composable
fun detailCardViewModel(
    pokemonId: Long
): DetailViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as MainActivity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).itemCardViewModelFactory()

    return viewModel(
        factory = DetailViewModel.provideFactory(
            factory,
            pokemonId = pokemonId
        )
    )
}

class DetailScreenFactory @Inject constructor() : NavigationScreenFactory {
    companion object Companion : NavigationFactory.NavigationFactoryCompanion {
        private const val POKEMON_ID_KEY = "pokemonId"
    }

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Nested)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = "$route/{$POKEMON_ID_KEY}",
            arguments = listOf(navArgument(POKEMON_ID_KEY) { type = NavType.LongType })
        ) {
            it.arguments?.getLong(POKEMON_ID_KEY)?.let { pokemonId ->
                DetailScreen(
                    viewModel = detailCardViewModel(
                        pokemonId = pokemonId
                    )
                )
            }
        }
    }
}