package com.vid.noteapp.domain.use_case

data class NoteUseCases(
    val addNote: AddNote,
    val deleteNote: DeleteNote,
    val getNote: GetNote,
    val getNotes: GetNotes
)