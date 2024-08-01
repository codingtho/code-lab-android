package com.codingtho.chuck_norris_jokes.data.repository

import com.codingtho.chuck_norris_jokes.data.remote.api.ApiService
import com.codingtho.chuck_norris_jokes.data.repository.model.Joke
import com.codingtho.chuck_norris_jokes.util.toItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JokeRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun getRandomJoke(): Joke {
        val response = api.getRandomJoke()
        return response.toItem()
    }
}
