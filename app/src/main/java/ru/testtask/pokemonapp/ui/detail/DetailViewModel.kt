package ru.testtask.pokemonapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.testtask.pokemonapp.domain.model.PokemonInfo
import ru.testtask.pokemonapp.domain.repository.PokemonsRepository
import ru.testtask.pokemonapp.ui.base.BaseEvent
import ru.testtask.pokemonapp.ui.base.BaseViewModel

class DetailViewModel @AssistedInject constructor(
    @Assisted
    private val pokemonId: Long,
    private val repository: PokemonsRepository
) : BaseViewModel<DetailViewModel.Event>() {

    val pokemon: StateFlow<PokemonInfo>
        get() = _pokemon.asStateFlow()

    private val _pokemon = MutableStateFlow(PokemonInfo.shimmerData)

    private fun startLoading() = viewModelScope.launch {
        _pokemon.emit(repository.getPokemonInfo(id = pokemonId))
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

    init {
        obtainEvent(Event.OnLoadingStarted)
    }

    @AssistedFactory
    interface Factory {
        fun create(
            pokemonId: Long
        ): DetailViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            pokemonId: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(
                    pokemonId
                ) as T
            }
        }
    }
}