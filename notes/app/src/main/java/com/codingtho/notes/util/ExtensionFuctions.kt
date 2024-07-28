package com.codingtho.notes.util

import com.codingtho.notes.data.local.entity.NoteEntity
import com.codingtho.notes.data.repository.model.Note

fun Note.toEntity() = NoteEntity(id, title, body)

fun NoteEntity.toItem() = Note(id, title, body)
