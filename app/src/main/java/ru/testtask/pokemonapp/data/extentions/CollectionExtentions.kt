package ru.testtask.pokemonapp.data.extentions

import ru.testtask.pokemonapp.di.navigation.NavigationFactory

fun Set<NavigationFactory>.filter(vararg filters: NavigationFactory.NavigationFactoryType): List<NavigationFactory> {
    val rezList = mutableListOf<NavigationFactory>()
    filters.forEach { type ->
        rezList.addAll(this.filter { it.factoryType.contains(type) })
    }
    return rezList.toList()
}