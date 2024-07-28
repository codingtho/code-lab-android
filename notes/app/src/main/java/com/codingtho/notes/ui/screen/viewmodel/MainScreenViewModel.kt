package com.codingtho.notes.ui.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtho.notes.data.repository.NoteRepository
import com.codingtho.notes.data.repository.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {
    private val _notes: MutableStateFlow<List<Note>> = MutableStateFlow(emptyList())
    val notes: StateFlow<List<Note>> get() = _notes.asStateFlow()

    init {
        fetchAllNotes()
    }

    private fun fetchAllNotes() {
        viewModelScope.launch {
            _notes.value = repository.getAllNotes()
        }
    }
}
