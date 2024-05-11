package ru.testtask.pokemonapp.data.remote.paging

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.testtask.pokemonapp.data.remote.PokemonsApi
import ru.testtask.pokemonapp.domain.model.Pokemons

class PokemonsListPagingSource(
    private val pokemonsApi: PokemonsApi
): PagingSource<Int, Pokemons>() {
    private val limit: Int = 30

    override fun getRefreshKey(state: PagingState<Int, Pokemons>): Int? =
        state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemons> {
        val page = params.key ?: 1
        return try {
            val pokemonsResp = pokemonsApi.getPokemonsList(limit = limit, offset = (page - 1) * limit)

            // id in url: https://pokeapi.co/api/v2/pokemon/1/
            val respItems = pokemonsResp.results.map { result ->  Pokemons(
                name = result.name,
                id = result.url.split('/', ignoreCase = true)[6].toInt()
            )}

            LoadResult.Page(
                data = respItems,
                nextKey = if (pokemonsResp.next != null) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception){
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

}