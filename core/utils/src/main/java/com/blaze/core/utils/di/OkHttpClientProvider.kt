package com.blaze.core.utils.di

import android.content.Context
import com.blaze.core.utils.data.local.AppDataStore
import com.blaze.core.utils.data.local.AppDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object OkHttpClientProvider {

    //for retrofit
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    //for ktor
    @Provides
    @Singleton
    fun provideHttpClient(appDataStore: AppDataStore): HttpClient {
        val accessToken: MutableStateFlow<String?> = MutableStateFlow(null)
        CoroutineScope(Dispatchers.IO).launch {
            appDataStore.getAccessToken().collect {
                it?.let {
                    accessToken.value = it
                }
            }
        }
        return HttpClient(OkHttp) {

            install(ContentNegotiation) {
                json(contentType = ContentType.Application.Json)
                json(contentType = ContentType.Application.FormUrlEncoded)
            }
            expectSuccess = true
            install(HttpTimeout) {
                socketTimeoutMillis = 60000
                requestTimeoutMillis = 60000
                connectTimeoutMillis = 60000
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                header(key = "Authorization", value = accessToken.value ?: "NA")

            }
            //ktor logger
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }
        }
    }

    @Provides
    @Singleton
    fun provideAppDataStore(@ApplicationContext context: Context): AppDataStore =
        AppDataStoreImpl(context = context)

}