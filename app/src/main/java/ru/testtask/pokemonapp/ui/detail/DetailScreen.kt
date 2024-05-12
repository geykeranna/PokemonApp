package ru.testtask.pokemonapp.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import dagger.hilt.android.EntryPointAccessors
import ru.testtask.pokemonapp.R
import ru.testtask.pokemonapp.ui.MainActivity
import ru.testtask.pokemonapp.di.navigation.NavigationFactory
import ru.testtask.pokemonapp.di.navigation.NavigationScreenFactory
import ru.testtask.pokemonapp.ui.detail.components.StatsInfo
import ru.testtask.pokemonapp.ui.detail.components.TypeInfo
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    navGraph: NavHostController
) {
    
    val detailInfo = viewModel.pokemon.collectAsState().value
    
    Column(
        modifier = Modifier.fillMaxSize(),

    ) {
        TopAppBar(
            title = { },
            modifier = Modifier.fillMaxWidth(),
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Color.Transparent,
            ),
            navigationIcon = {
                IconButton(
                    onClick = {
                        if (navGraph.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED)
                            navGraph.popBackStack()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "back button"
                    )
                }
            }
        )
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clip(RoundedCornerShape(20.dp)),
            model = detailInfo.imgURL,
            contentDescription = "Pokemon img"
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = detailInfo.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(java.util.Locale.US) else it.toString()
            }.replace('-', ' '),
            fontSize = 42.sp,
            textAlign = TextAlign.Center
        )

        TypeInfo(types = detailInfo.types)

        StatsInfo(
            modifier = Modifier.padding(bottom = 20.dp),
            stats = detailInfo.stats
        )
    }

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
                    ),
                    navGraph = navGraph
                )
            }
        }
    }
}