package ru.testtask.pokemonapp.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.testtask.pokemonapp.domain.model.Pokemons

interface PokemonsRepository {

    fun getProductList(): Flow<PagingData<Pokemons>>
}