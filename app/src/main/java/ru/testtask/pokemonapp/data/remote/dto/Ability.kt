package ru.testtask.pokemonapp.data.remote.dto

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)