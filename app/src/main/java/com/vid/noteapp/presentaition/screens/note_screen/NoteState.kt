package com.vid.noteapp.presentaition.screens.note_screen

import com.vid.noteapp.domain.models.Note
import com.vid.noteapp.domain.util.NoteOrder
import com.vid.noteapp.domain.util.OrderType

data class NoteState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)