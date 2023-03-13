package com.schaefer.lorempicsum.core.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.schaefer.lorempicsum.BuildConfig
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

// private const val SEVEN_DAYS_MAX_STALE = 60 * 60 * 24 * 7
private const val SIXTY_SECONDS_MAX_AGE = 60

val networkModule = module {

    single<OkHttpClient> { provideOkHttpClient() }

    single<Retrofit> {
        provideRetrofit(
            okHttpClient = get(),
        )
    }
}

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            var request = chain.request()
            request.newBuilder().header(
                "Cache-Control",
                "public, max-age=$SIXTY_SECONDS_MAX_AGE"
            ).build()
            chain.proceed(request)
        }
        .build()
} else {
    OkHttpClient
        .Builder()
        .build()
}

private val json = Json { ignoreUnknownKeys = true }

@OptIn(ExperimentalSerializationApi::class)
private fun provideRetrofit(
    okHttpClient: OkHttpClient,
): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()
}
