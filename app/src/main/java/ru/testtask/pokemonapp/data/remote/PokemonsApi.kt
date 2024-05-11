package ru.testtask.pokemonapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.testtask.pokemonapp.data.remote.dto.AllPokemonGetResponse

interface PokemonsApi {

    @GET("pokemon")
    suspend fun getPokemonsList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): AllPokemonGetResponse
}