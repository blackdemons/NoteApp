package com.vid.noteapp.data.repository

import com.vid.noteapp.data.local.NoteDAO
import com.vid.noteapp.domain.models.Note
import com.vid.noteapp.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(
     private val dao: NoteDAO
): MainRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        return dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note)
    }

}