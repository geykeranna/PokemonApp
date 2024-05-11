package ru.testtask.pokemonapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.testtask.pokemonapp.data.remote.dto.AllPokemonGetResponse
import ru.testtask.pokemonapp.data.remote.dto.PokemonGetOneResponce

interface PokemonsApi {

    @GET("pokemon")
    suspend fun getPokemonsList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): AllPokemonGetResponse

    @GET("pokemon/{id}")
    suspend fun getOnePokemon(
        @Path("id") id: Long,
    ): PokemonGetOneResponce

}