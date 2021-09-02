package com.mubarakansari.preferences_datastore_android_kotlin.Repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

const val PREFERENCE_NAME = "my_preference"

class DataStoreRepository(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PREFERENCE_NAME)

    companion object {
        val EditTexts = stringPreferencesKey("EDIT_TEXT")
    }

    suspend fun storeData (editText: String){
       context.dataStore.edit {
           it[EditTexts] = editText
       }
    }
    fun getData()= context.dataStore.data.map {
        it[EditTexts] ?: ""

    }

}