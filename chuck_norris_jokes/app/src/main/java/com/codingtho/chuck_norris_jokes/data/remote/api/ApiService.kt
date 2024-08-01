package com.codingtho.chuck_norris_jokes.data.remote.api

import com.codingtho.chuck_norris_jokes.data.remote.response.JokeResponse
import retrofit2.http.GET

interface ApiService {

    @GET("random")
    suspend fun getRandomJoke(): JokeResponse
}
