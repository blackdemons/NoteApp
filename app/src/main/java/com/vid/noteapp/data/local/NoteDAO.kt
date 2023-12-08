package com.vid.noteapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vid.noteapp.domain.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Query("Select * From notes")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE noteId = :noteId")
    suspend fun getNoteById(noteId: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}