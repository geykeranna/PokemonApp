package ru.testtask.pokemonapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import ru.testtask.pokemonapp.data.extentions.filter
import ru.testtask.pokemonapp.di.navigation.NavigationFactory
import ru.testtask.pokemonapp.di.navigation.NavigationHostFactory
import ru.testtask.pokemonapp.di.navigation.NavigationScreenFactory
import ru.testtask.pokemonapp.ui.detail.DetailViewModel
import ru.testtask.pokemonapp.ui.home.HomeViewModel
import ru.testtask.pokemonapp.ui.navigation.NavItem
import ru.testtask.pokemonapp.ui.theme.PokemonAppTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationScreenFactorySet: @JvmSuppressWildcards Set<NavigationScreenFactory>

    @Inject
    lateinit var navigationHostFactorySet: @JvmSuppressWildcards Set<NavigationHostFactory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokemonAppTheme {
                val navController = rememberNavController()

                Surface {
                    NavHost(
                        navController = navController,
                        startDestination = NavItem.MainScreen.route
                    ) {
                        mutableSetOf<NavigationFactory>().apply {
                            addAll(
                                navigationScreenFactorySet
                                    .filter(NavigationFactory.NavigationFactoryType.Main)
                            )
                            addAll(
                                navigationHostFactorySet
                                    .filter(NavigationFactory.NavigationFactoryType.Main)
                            )
                        }.forEach {
                            it.create(this, navController)
                        }
                    }
                }
            }
        }
    }

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun itemCardViewModelFactory(): DetailViewModel.Factory
    }
}
