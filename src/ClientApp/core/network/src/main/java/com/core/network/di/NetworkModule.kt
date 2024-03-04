package com.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

internal const val BASE_URL = "http://10.0.2.2:8080/"

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
    }

    @Provides
    @Singleton
//    fun provideOkhttpWithLogging(): OkHttpClient {
    fun provideOkhttpWithLogging(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .callTimeout(10_000L, TimeUnit.MILLISECONDS)
            .connectTimeout(10_000L, TimeUnit.MILLISECONDS)
            .readTimeout(10_000L, TimeUnit.MILLISECONDS)
            .writeTimeout(10_000L, TimeUnit.MILLISECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory(contentType))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideRetrofitCompanyService(retrofit: Retrofit): com.core.network.CompanyApiService {
        return retrofit.create(com.core.network.CompanyApiService::class.java)
    }

    @Provides
    fun provideRetrofitVacancyService(retrofit: Retrofit): com.core.network.VacancyApiService {
        return retrofit.create(com.core.network.VacancyApiService::class.java)
    }

}