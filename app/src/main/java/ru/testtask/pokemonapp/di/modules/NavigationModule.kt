package ru.testtask.pokemonapp.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import ru.testtask.pokemonapp.di.navigation.NavigationHostFactory
import ru.testtask.pokemonapp.di.navigation.NavigationScreenFactory
import ru.testtask.pokemonapp.ui.detail.DetailScreenFactory
import ru.testtask.pokemonapp.ui.home.HomeScreenFactory
import ru.testtask.pokemonapp.ui.navigation.MainNavHostScreenFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @IntoSet
    @Binds
    @Singleton
    fun bindMainNavHostScreenFactory(mainNavHostScreenFactory: MainNavHostScreenFactory): NavigationHostFactory

    @IntoSet
    @Binds
    @Singleton
    fun bindHomeScreenFactory(homeScreenFactory: HomeScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @Singleton
    fun bindDetailCardScreenFactory(detailCardScreenFactory: DetailScreenFactory): NavigationScreenFactory
}