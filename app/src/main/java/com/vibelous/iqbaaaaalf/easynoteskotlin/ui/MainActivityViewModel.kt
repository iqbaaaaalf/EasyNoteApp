package com.vibelous.iqbaaaaalf.easynoteskotlin.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.vibelous.iqbaaaaalf.easynoteskotlin.AppExecutors
import com.vibelous.iqbaaaaalf.easynoteskotlin.data.EasyNoteRepository
import com.vibelous.iqbaaaaalf.easynoteskotlin.data.database.NoteDao
import com.vibelous.iqbaaaaalf.easynoteskotlin.data.database.NoteEntity

/**
 * Created by iqbaaaaalf on 12/10/2017.
 */
class MainActivityViewModel(noteDao: NoteDao, executor: AppExecutors) : ViewModel() {

    val TAG: String = this.javaClass.simpleName
    var noteList: LiveData<List<NoteEntity>>
    var mDao: NoteDao = noteDao
    var mExecutor: AppExecutors = executor

    init {
        noteList = getAllNotes()
    }

    fun addNote(newNote: NoteEntity){
        mExecutor.diskIO.execute {
            mDao.insertNote(newNote)
            Log.d(TAG, "New note added")
        }
    }

    fun getAllNotes(): LiveData<List<NoteEntity>>{
        return mDao.getAllNotes()
    }

//    var mDb: EasyNoteDatabase? = EasyNoteDatabase.getInstance(getApplication())




}