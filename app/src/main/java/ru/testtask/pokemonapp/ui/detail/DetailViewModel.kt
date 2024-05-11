package ru.testtask.pokemonapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.testtask.pokemonapp.domain.repository.PokemonsRepository
import ru.testtask.pokemonapp.ui.base.BaseEvent
import ru.testtask.pokemonapp.ui.base.BaseViewModel
import javax.inject.Inject

class DetailViewModel @AssistedInject constructor(
    @Assisted
    private val pokemonId: Long,
    private val repository: PokemonsRepository
) : BaseViewModel<DetailViewModel.Event>() {

    sealed class Event : BaseEvent() {
        data object OnLoadingStarted : Event()
    }

    override fun obtainEvent(event: Event) {
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