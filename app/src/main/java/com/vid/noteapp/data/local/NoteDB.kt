package com.vid.noteapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vid.noteapp.domain.models.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDB : RoomDatabase() {
    abstract val noteDAO: NoteDAO

    companion object{
        const val DATABASE_NAME = "notes_db"
    }
}