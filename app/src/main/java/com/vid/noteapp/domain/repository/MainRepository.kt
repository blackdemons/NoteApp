package com.vid.noteapp.domain.repository

import com.vid.noteapp.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getAllNotes(): Flow<List<Note>>
    suspend fun getNoteById(id: Int): Note?
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)

}