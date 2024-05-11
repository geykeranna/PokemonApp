package ru.testtask.pokemonapp.ui.home

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.testtask.pokemonapp.ui.base.BaseEvent
import ru.testtask.pokemonapp.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : BaseViewModel<HomeViewModel.Event>() {

    sealed class Event : BaseEvent() {
    }

    override fun obtainEvent(event: Event) {
    }
}