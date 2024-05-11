package ru.testtask.pokemonapp.ui.navigation



sealed class NavItem(val route: String) {

    data object MainScreen : NavItem(MainNavHostScreenFactory.route)

}