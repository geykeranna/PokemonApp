package ru.testtask.pokemonapp.domain.model


data class PokemonInfo(
    val id: Int,
    val abilities: List<String>,
    val baseExperience: Int,
    val name: String,
    val order: Int,
    val stats: List<Stat>,
    val types: List<String>,
    val imgURL: String,
) {
    companion object{
        val shimmerData = PokemonInfo(
            id = 0,
            name = "",
            stats = listOf(),
            order = 0,
            abilities = listOf(),
            baseExperience = 0,
            types = listOf(),
            imgURL = ""
        )
    }
}

data class Stat(
    val baseStat: Int,
    val effort: Int,
    val name: String
)