package com.codingtho.chuck_norris_jokes.util

import com.codingtho.chuck_norris_jokes.data.remote.response.JokeResponse
import com.codingtho.chuck_norris_jokes.data.repository.model.Joke

fun JokeResponse.toItem() = Joke(
    categories = categories,
    icon = iconUrl,
    value = value
)
