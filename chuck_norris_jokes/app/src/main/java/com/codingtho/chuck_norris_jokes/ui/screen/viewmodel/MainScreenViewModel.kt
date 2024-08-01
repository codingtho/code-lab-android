package com.codingtho.chuck_norris_jokes.ui.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtho.chuck_norris_jokes.data.repository.JokeRepository
import com.codingtho.chuck_norris_jokes.data.repository.model.Joke
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: JokeRepository
) : ViewModel() {
    private val _joke: MutableStateFlow<Joke> = MutableStateFlow(
        Joke(
            categories = emptyList(),
            icon = "",
            value = ""
        )
    )
    val joke: StateFlow<Joke> get() = _joke.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()

    init {
        getRandomJoke()
    }

    fun getRandomJoke() {
        viewModelScope.launch {
            _joke.value = repository.getRandomJoke()
            _isLoading.value = false
        }
    }
}
