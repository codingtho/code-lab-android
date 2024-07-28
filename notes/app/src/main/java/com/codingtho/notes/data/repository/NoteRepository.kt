package com.codingtho.notes.data.repository

import com.codingtho.notes.data.local.dao.NoteDao
import com.codingtho.notes.data.local.entity.NoteEntity
import com.codingtho.notes.data.repository.model.Note
import com.codingtho.notes.util.toEntity
import com.codingtho.notes.util.toItem
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {
    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note.toEntity())
    }

    suspend fun getAllNotes(): List<Note> {
        val response: List<NoteEntity> = noteDao.getAllNotes()
        return response.map { note -> note.toItem() }
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note.toEntity())
    }
}
