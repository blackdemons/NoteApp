package com.vid.noteapp.presentaition.screens.note_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vid.noteapp.domain.models.Note
import com.vid.noteapp.domain.use_case.NoteUseCases
import com.vid.noteapp.domain.util.NoteOrder
import com.vid.noteapp.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
) : ViewModel() {
    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(events: NoteEvents) {
        when (events) {
            is NoteEvents.Order -> {
                if (state.value.noteOrder::class == events.noteOrder::class &&
                    state.value.noteOrder.orderType == events.noteOrder.orderType
                ) {
                    return
                }
                getNotes(events.noteOrder)
            }
            is NoteEvents.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(events.note)
                    recentlyDeletedNote = events.note
                }
            }
            is NoteEvents.RestoreNote ->{
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NoteEvents.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }

}
