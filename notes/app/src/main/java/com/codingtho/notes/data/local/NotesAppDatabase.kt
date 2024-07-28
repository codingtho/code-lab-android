package com.codingtho.notes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingtho.notes.data.local.dao.NoteDao
import com.codingtho.notes.data.local.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesAppDatabase: RoomDatabase() {

    abstract fun getNoteDao(): NoteDao
}
