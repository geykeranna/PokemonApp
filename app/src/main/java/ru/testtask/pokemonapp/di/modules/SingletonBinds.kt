package ru.testtask.pokemonapp.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//@Module
//@InstallIn(SingletonComponent::class)
//interface SingletonBinds {
//
//    @Binds
//    @Singleton
//    fun bindTarotSystemRepository(tarotSystemRepositoryImpl: TarotSystemRepositoryImpl) : TarotSystemRepository
//
//    @Binds
//    fun bindGroupOfSuitsRepository(groupOfSuitsRepositoryImpl: GroupOfSuitsRepositoryImpl) : GroupOfSuitsRepository
//
//    @Binds
//    fun bindDetailCardRepository(cardRepositoryImpl: CardRepositoryImpl) : CardRepository
//}