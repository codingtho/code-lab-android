package com.codingtho.notes.ui.screen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.codingtho.notes.data.repository.model.Note
import com.codingtho.notes.ui.screen.viewmodel.MainScreenViewModel
import com.google.gson.Gson

@Composable
fun MainScreen(viewModel: MainScreenViewModel = hiltViewModel(), navController: NavController) {
    val notes by viewModel.notes.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(notes) { note ->
            ShowNote(note = note, navController = navController)
        }
    }
}

@Composable
private fun ShowNote(note: Note, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.DarkGray, shape = RoundedCornerShape(8.dp))
            .clickable(onClick = {
                val json = Gson().toJson(note)
                navController.navigate("add_or_edit?note=$json")
            })
    ) {
        Text(
            text = note.title,
            modifier = Modifier.padding(8.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )

        note.body?.let { body ->
            Text(
                text = body,
                modifier = Modifier.padding(8.dp),
                fontSize = 14.sp,
                maxLines = 2
            )
        }
    }
}
