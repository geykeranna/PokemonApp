package ru.testtask.pokemonapp.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.testtask.pokemonapp.domain.model.Pokemons
import ru.testtask.pokemonapp.domain.repository.PokemonsRepository
import ru.testtask.pokemonapp.ui.base.BaseEvent
import ru.testtask.pokemonapp.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PokemonsRepository
) : BaseViewModel<HomeViewModel.Event>() {

    val pokemonList: Flow<PagingData<Pokemons>>
        get() = _pokemonList.cachedIn(viewModelScope)

    private lateinit var _pokemonList: Flow<PagingData<Pokemons>>

    init {
        obtainEvent(Event.OnLoadingStarted)
    }

    private fun startLoading() = viewModelScope.launch {
        _pokemonList = repository.getPokemonsList()
    }

    sealed class Event : BaseEvent() {
        data object OnLoadingStarted : Event()
    }

    override fun obtainEvent(event: Event) {
        when(event) {
            is Event.OnLoadingStarted -> {
                startLoading()
            }
        }
    }
}