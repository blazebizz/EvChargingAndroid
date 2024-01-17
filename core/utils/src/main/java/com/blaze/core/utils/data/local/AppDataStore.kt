package com.blaze.core.utils.data.local

import kotlinx.coroutines.flow.Flow

interface AppDataStore {

     suspend fun saveAccessToken(accessToken: String)

     fun getAccessToken(): Flow<String?>
}