package com.core.network.di

import com.core.network.CompanyApiService
import com.core.network.ResumeApiService
import com.core.network.VacancyApiService
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
    fun provideRetrofitCompanyService(retrofit: Retrofit): CompanyApiService {
        return retrofit.create(CompanyApiService::class.java)
    }

    @Provides
    fun provideRetrofitVacancyService(retrofit: Retrofit): VacancyApiService {
        return retrofit.create(VacancyApiService::class.java)
    }

    @Provides
    fun provideRetrofitResumeService(retrofit: Retrofit): ResumeApiService {
        return retrofit.create(ResumeApiService::class.java)
    }

}