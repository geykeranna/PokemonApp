package ru.testtask.pokemonapp.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.testtask.pokemonapp.data.remote.PokemonsApi
import ru.testtask.pokemonapp.data.remote.repository.PokemonsRepositoryImpl
import ru.testtask.pokemonapp.domain.repository.PokemonsRepository
import ru.testtask.pokemonapp.utils.Constants.BASE_URL
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApi(): PokemonsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRepository(
        pokemonsApi: PokemonsApi
    ): PokemonsRepository = PokemonsRepositoryImpl(pokemonsApi)
}