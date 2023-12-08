package com.vid.noteapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vid.noteapp.presentaition.ui.theme.BabyBlue
import com.vid.noteapp.presentaition.ui.theme.LightGreen
import com.vid.noteapp.presentaition.ui.theme.RedOrange
import com.vid.noteapp.presentaition.ui.theme.RedPink
import com.vid.noteapp.presentaition.ui.theme.Violet

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey
    val noteId: Int? = null,
    val noteTitle: String,
    val noteDescription: String,
    val timestamp: Long,
    val color: Int,
){
    companion object{
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}


class InvalidNoteException(message: String): Exception(message)