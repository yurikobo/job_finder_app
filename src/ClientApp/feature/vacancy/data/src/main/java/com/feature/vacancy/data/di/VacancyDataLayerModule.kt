package com.feature.vacancy.data.di

import com.core.network.VacancyApiService
import com.feature.vacancy.data.repositories.VacancyRepositoryImpl
import com.feature.vacancy.domain.repositories.VacancyRepository
import dagger.Module
import dagger.Provides

@Module
class VacancyDataLayerModule {

    @Provides
    fun provideVacanciesRepository(vacancyApiService: VacancyApiService): VacancyRepository {
        return VacancyRepositoryImpl(vacancyApiService)
    }
}