package com.feature.resume.data.di

import com.core.network.ResumeApiService
import com.feature.resume.data.repository.ResumeRepositoryImpl
import com.feature.resume.domain.repositories.ResumeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ResumeDataLayerModule {

    @Provides
    @Singleton
    fun provideResumeRepository(resumeApiService: ResumeApiService): ResumeRepository {
        return ResumeRepositoryImpl(resumeApiService)
    }

}