package com.codingtho.notes.ui.screen.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.codingtho.notes.data.repository.model.Note
import com.codingtho.notes.ui.screen.Screen
import com.codingtho.notes.ui.screen.viewmodel.AddOrEditScreenViewModel

@Composable
fun AddOrEditScreen(
    viewModel: AddOrEditScreenViewModel = hiltViewModel(),
    navController: NavController,
    note: Note?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        NoteConfiguration(viewModel = viewModel, navController = navController, note = note)

        CancelOrSaveButtons(viewModel = viewModel, navController = navController, note = note)
    }
}

@Composable
private fun NoteConfiguration(
    viewModel: AddOrEditScreenViewModel,
    navController: NavController,
    note: Note?
) {
    val title by viewModel.title.collectAsState()
    val body by viewModel.body.collectAsState()

    LaunchedEffect(note) {
        note?.let {
            viewModel.initialize(it)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        InputField(
            label = "Title",
            text = title,
            onValueChange = { input ->
                viewModel.updateTitle(input)
            },
            maxLines = 1
        )

        Spacer(modifier = Modifier.padding(8.dp))

        InputField(
            modifier = Modifier.height(240.dp),
            label = "Body",
            text = body,
            onValueChange = { input ->
                viewModel.updateBody(input)
            },
            maxLines = 8
        )

        if (note != null) {
            Spacer(modifier = Modifier.padding(8.dp))

            DeleteButton(viewModel = viewModel, navController = navController, note = note)
        }
    }
}

@Composable
private fun DeleteButton(
    viewModel: AddOrEditScreenViewModel,
    navController: NavController,
    note: Note
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp),
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(onClick = {
            viewModel.deleteNote(note)
            viewModel.cleanParameters()
            navController.navigate(Screen.MainScreen.route)
        }) {
            Icon(imageVector = Icons.Rounded.Delete, contentDescription = null)
        }
    }
}

@Composable
private fun InputField(
    modifier: Modifier = Modifier,
    label: String,
    text: String,
    onValueChange: (String) -> Unit,
    maxLines: Int
) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(text = label) },
        maxLines = maxLines,
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
private fun CancelOrSaveButtons(
    viewModel: AddOrEditScreenViewModel,
    navController: NavController,
    note: Note?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedButton(onClick = {
            viewModel.cleanParameters()
            navController.navigate(Screen.MainScreen.route)
        }, modifier = Modifier.width(96.dp)) {
            Text(text = "Cancel")
        }

        FilledTonalButton(onClick = {
            viewModel.loadNote(
                Note(
                    id = note?.id ?: 0,
                    title = viewModel.title.value.ifBlank { "Title" },
                    body = viewModel.body.value.ifBlank { null }
                )
            )
            viewModel.cleanParameters()
            navController.navigate(Screen.MainScreen.route)
        }, modifier = Modifier.width(96.dp)) {
            if (note == null) Text(text = "Add") else Text(text = "Save")
        }
    }
}
