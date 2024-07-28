package com.codingtho.notes.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingtho.notes.data.local.entity.NoteEntity

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Query("SELECT * FROM note_table")
    suspend fun getAllNotes(): List<NoteEntity>

    @Delete
    suspend fun deleteNote(note: NoteEntity)
}
