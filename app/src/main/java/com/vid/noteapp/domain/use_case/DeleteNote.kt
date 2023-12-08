package com.vid.noteapp.domain.use_case

import com.vid.noteapp.domain.models.Note
import com.vid.noteapp.domain.repository.MainRepository

class DeleteNote(
    private val repository: MainRepository
) {
    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }
}