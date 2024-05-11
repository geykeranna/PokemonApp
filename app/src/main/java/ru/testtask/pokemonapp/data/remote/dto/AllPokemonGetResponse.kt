package ru.testtask.pokemonapp.data.remote.dto

data class AllPokemonGetResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Result>
)