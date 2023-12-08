package com.vid.noteapp.presentaition.screens.note_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vid.noteapp.presentaition.navigation.MainRoute
import com.vid.noteapp.presentaition.screens.note_screen.component.NoteItem
import com.vid.noteapp.presentaition.screens.note_screen.component.OrderSection
import kotlinx.coroutines.launch

@Composable
fun NoteScreen(
    navController: NavController,
    viewModel: NoteViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(MainRoute.AddNoteScreen.route)
                }, backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        }, scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your note", style = MaterialTheme.typography.h4
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(NoteEvents.ToggleOrderSection)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.List, contentDescription = "Sort"
                    )
                }

            }
            AnimatedVisibility(
                visible = state.value.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                    noteOrder = state.value.noteOrder,
                    onOrderChange = {
                        viewModel.onEvent(NoteEvents.Order(it))
                    })
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.value.notes) { note ->
                    NoteItem(
                        note = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    MainRoute.AddNoteScreen.route +
                                            "?noteId=${note.noteId}&noteColor=${note.color}"
                                )
                            },
                        onDeleteClick = {
                            viewModel.onEvent(NoteEvents.DeleteNote(note))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Note delete",
                                    actionLabel = "Undo"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(NoteEvents.RestoreNote)
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                }
            }
        }
    }
}