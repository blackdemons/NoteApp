package com.vid.noteapp.domain.use_case

import com.vid.noteapp.domain.models.InvalidNoteException
import com.vid.noteapp.domain.models.Note
import com.vid.noteapp.domain.repository.MainRepository

class AddNote(
    private val repository: MainRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if(note.noteTitle.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        if(note.noteDescription.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        repository.insertNote(note)
    }
}