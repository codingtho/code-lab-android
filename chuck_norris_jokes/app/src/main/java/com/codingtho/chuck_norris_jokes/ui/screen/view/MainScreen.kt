package com.codingtho.chuck_norris_jokes.ui.screen.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.codingtho.chuck_norris_jokes.data.repository.model.Joke
import com.codingtho.chuck_norris_jokes.ui.screen.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(viewModel: MainScreenViewModel = hiltViewModel()) {
    val joke by viewModel.joke.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                LoadingScreen()
            } else {
                Spacer(modifier = Modifier.weight(1f))

                ShowJoke(joke = joke)

                Spacer(modifier = Modifier.weight(1f))

                GetJokeButton(viewModel = viewModel)
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(64.dp)
        )
    }
}

@Composable
private fun ShowJoke(joke: Joke) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = joke.value,
            modifier = Modifier
                .padding(16.dp)
                .weight(2f),
            fontSize = 16.sp
        )

        AsyncImage(
            model = joke.icon,
            contentDescription = null,
            modifier = Modifier
                .size(128.dp)
                .padding(16.dp)
                .weight(1f)
        )
    }
}

@Composable
private fun GetJokeButton(viewModel: MainScreenViewModel) {
    TextButton(
        onClick = {
            viewModel.getRandomJoke()
        },
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = "Get a joke"
        )
    }
}
