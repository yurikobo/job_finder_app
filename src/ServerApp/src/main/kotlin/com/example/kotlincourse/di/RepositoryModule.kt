package com.example.kotlincourse.di

import com.example.kotlincourse.data.repository.*
import com.example.kotlincourse.domain.repository.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideVacancyRepository(): VacancyRepository {
        return VacancyRepositoryExposedImpl()
    }

    @Provides
    @Singleton
    fun provideCompanyRepository(vacancyRepository: VacancyRepository): CompanyRepository {
        return CompanyRepositoryExposedImpl(vacancyRepository)
    }

    @Provides
    @Singleton
    fun provideContactRepository(): ContactRepository {
        return ContactRepositoryExposedImpl()
    }

    @Provides
    @Singleton
    fun provideCandidateInfoRepository(contactRepository: ContactRepository): CandidateInfoRepository {
        return CandidateInfoRepositoryExposedImpl(contactRepository)
    }

    @Provides
    @Singleton
    fun provideEducationRepository(): EducationRepository {
        return EducationRepositoryExposedImpl()
    }

    @Provides
    @Singleton
    fun provideJobExperienceRepository(): JobExperienceRepository {
        return JobExperienceRepositoryExposedImpl()
    }

    @Provides
    @Singleton
    fun provideResumeRepository(
        candidateInfoRepository: CandidateInfoRepository,
        educationRepository: EducationRepository,
        jobExperienceRepository: JobExperienceRepository
    ): ResumeRepository {
        return ResumeRepositoryExposedImpl(candidateInfoRepository, educationRepository, jobExperienceRepository)
    }
}