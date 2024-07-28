package com.codingtho.notes.di

import android.content.Context
import androidx.room.Room
import com.codingtho.notes.data.local.NotesAppDatabase
import com.codingtho.notes.data.local.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NotesAppDatabase {
        return Room.databaseBuilder(context, NotesAppDatabase::class.java, "notes_app_database").build()
    }

    @Singleton
    @Provides
    fun provideNoteDao(database: NotesAppDatabase): NoteDao {
        return database.getNoteDao()
    }
}
