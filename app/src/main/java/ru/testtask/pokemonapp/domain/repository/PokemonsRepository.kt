package ru.testtask.pokemonapp.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.testtask.pokemonapp.domain.model.PokemonInfo
import ru.testtask.pokemonapp.domain.model.Pokemons

interface PokemonsRepository {

    fun getPokemonsList(): Flow<PagingData<Pokemons>>

    suspend fun getPokemonInfo(id: Long): PokemonInfo
}