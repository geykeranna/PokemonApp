package ru.testtask.pokemonapp.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.testtask.pokemonapp.data.remote.PokemonsApi
import ru.testtask.pokemonapp.data.remote.paging.PokemonsListPagingSource
import ru.testtask.pokemonapp.domain.model.PokemonInfo
import ru.testtask.pokemonapp.domain.model.Pokemons
import ru.testtask.pokemonapp.domain.model.Stat
import ru.testtask.pokemonapp.domain.repository.PokemonsRepository
import javax.inject.Inject

class PokemonsRepositoryImpl @Inject constructor(
    private val pokemonsApi: PokemonsApi
): PokemonsRepository {
    override fun getPokemonsList(): Flow<PagingData<Pokemons>> =
        Pager(
            config = PagingConfig(pageSize = 30),
            pagingSourceFactory = {
                PokemonsListPagingSource(
                    pokemonsApi = pokemonsApi
                )
            }
        ).flow

    override suspend fun getPokemonInfo(id: Long): PokemonInfo {
        val item = pokemonsApi.getOnePokemon(id)
        return PokemonInfo(
            id = item.id,
            name = item.name,
            baseExperience = item.base_experience,
            abilities = item.abilities.map { it.ability.name },
            order = item.order,
            stats = item.stats.map { Stat(
                baseStat = it.base_stat,
                name = it.stat.name,
                effort = it.effort
            )},
            types = item.types.map { it.type.name },
            imgURL = item.sprites.front_default
        )
    }
}