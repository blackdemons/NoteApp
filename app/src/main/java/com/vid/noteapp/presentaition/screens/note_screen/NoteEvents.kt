package com.vid.noteapp.presentaition.screens.note_screen

import com.vid.noteapp.domain.models.Note
import com.vid.noteapp.domain.util.NoteOrder

sealed class NoteEvents {
    data class Order(val noteOrder: NoteOrder): NoteEvents()
    data class DeleteNote(val note: Note): NoteEvents()
    data object RestoreNote: NoteEvents()
    data object ToggleOrderSection: NoteEvents()
}