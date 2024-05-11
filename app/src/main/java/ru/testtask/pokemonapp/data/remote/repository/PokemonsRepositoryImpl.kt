package ru.testtask.pokemonapp.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.testtask.pokemonapp.data.remote.PokemonsApi
import ru.testtask.pokemonapp.data.remote.paging.PokemonsListPagingSource
import ru.testtask.pokemonapp.domain.model.Pokemons
import ru.testtask.pokemonapp.domain.repository.PokemonsRepository

class PokemonsRepositoryImpl(
    private val pokemonsApi: PokemonsApi
): PokemonsRepository {
    override fun getProductList(): Flow<PagingData<Pokemons>> =
        Pager(
            config = PagingConfig(pageSize = 30),
            pagingSourceFactory = {
                PokemonsListPagingSource(
                    pokemonsApi = pokemonsApi
                )
            }
        ).flow

}