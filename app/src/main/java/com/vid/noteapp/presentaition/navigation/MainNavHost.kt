package com.vid.noteapp.presentaition.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vid.noteapp.presentaition.screens.note_add_screen.AddNoteScreen
import com.vid.noteapp.presentaition.screens.note_screen.NoteScreen

sealed class MainRoute(val route: String) {
    data object NoteScreen : MainRoute("note_screen")
    data object AddNoteScreen : MainRoute("add_note_screen")
}

@Composable
fun MainNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = MainRoute.NoteScreen.route) {
        composable(MainRoute.NoteScreen.route){
            NoteScreen(navController = navController)
        }
        composable(MainRoute.AddNoteScreen.route +
                "?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "noteColor"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            val color = it.arguments?.getInt("noteColor") ?: -1
            AddNoteScreen(navController = navController, noteColor = color)
        }

    }
}