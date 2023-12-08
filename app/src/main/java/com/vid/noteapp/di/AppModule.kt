package com.vid.noteapp.di

import android.app.Application
import androidx.room.Room
import com.vid.noteapp.data.local.NoteDB
import com.vid.noteapp.data.repository.MainRepositoryImp
import com.vid.noteapp.domain.repository.MainRepository
import com.vid.noteapp.domain.use_case.AddNote
import com.vid.noteapp.domain.use_case.DeleteNote
import com.vid.noteapp.domain.use_case.GetNote
import com.vid.noteapp.domain.use_case.GetNotes
import com.vid.noteapp.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDB {
        return Room.databaseBuilder(
            app,
            NoteDB::class.java,
            NoteDB.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDB) : MainRepository{
        return MainRepositoryImp(db.noteDAO)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: MainRepository): NoteUseCases{
        return NoteUseCases(
            addNote = AddNote(repository),
            deleteNote = DeleteNote(repository),
            getNote = GetNote(repository),
            getNotes = GetNotes(repository)
        )
    }

}
