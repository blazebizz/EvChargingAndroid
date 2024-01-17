package com.blaze.core.utils.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.blaze.core.utils.util.PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import androidx.datastore.preferences.core.Preferences
import com.blaze.core.utils.util.ACCESS_TOKEN
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class AppDataStoreImpl(context: Context) :AppDataStore {
    private val dataStore = context.dataStore
    private object PreferencesKey {
        val accessTokenKey = stringPreferencesKey(name = ACCESS_TOKEN)
    }

    override suspend fun saveAccessToken(
        accessToken: String
    ) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.accessTokenKey] = accessToken
         }
    }
    override fun getAccessToken(): Flow<String?> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val accessToken = preferences[PreferencesKey.accessTokenKey]
            accessToken
        }
    }

}