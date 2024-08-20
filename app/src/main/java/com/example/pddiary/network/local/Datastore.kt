package com.example.pddiary.network.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Locale

const val PREFERENCES_NAME = "my_preferences"

class Datastore(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

    private val previousDates = stringSetPreferencesKey("PREVIOUS_DATES")


    suspend fun addStringToList(context: Context, newEntry: String) {
        context.dataStore.edit { preferences ->
            val currentList = preferences[previousDates]?.toMutableSet() ?: mutableSetOf()
            currentList.add(newEntry)
            val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val sortedList = currentList
                .map { sdf.parse(it) }
                .sorted()
                .map { sdf.format(it) }
                .toMutableSet()
            preferences[previousDates] = sortedList
        }
    }

    fun getStringList(): Flow<List<String>> {
        return context.dataStore.data
            .map { preferences ->
                preferences[previousDates]?.toList() ?: emptyList()
            }
    }
}