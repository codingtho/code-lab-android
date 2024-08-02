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
class AddOrEditScreenViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    private val _title: MutableStateFlow<String> = MutableStateFlow("")
    val title: StateFlow<String> get() = _title.asStateFlow()

    private val _body: MutableStateFlow<String> = MutableStateFlow("")
    val body: StateFlow<String> get() = _body.asStateFlow()

    fun initialize(note: Note) {
        _title.value = note.title
        _body.value = note.body ?: ""
    }

    fun updateTitle(title: String) {
        _title.value = title
    }

    fun updateBody(body: String) {
        _body.value = body
    }

    fun cleanParameters() {
        _title.value = ""
        _body.value = ""
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    fun loadNote(note: Note) {
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }
}
