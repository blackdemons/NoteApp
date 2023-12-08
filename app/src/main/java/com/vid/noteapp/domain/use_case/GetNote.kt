package com.vid.noteapp.domain.use_case

import com.vid.noteapp.domain.models.Note
import com.vid.noteapp.domain.repository.MainRepository

class GetNote(
    private val repository: MainRepository
) {
    suspend operator fun invoke(id: Int): Note?{
        return repository.getNoteById(id)
    }
}