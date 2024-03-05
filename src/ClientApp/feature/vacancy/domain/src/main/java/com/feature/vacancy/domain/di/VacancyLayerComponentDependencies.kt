package com.feature.vacancy.domain.di

import com.feature.vacancy.domain.repositories.VacancyRepository

interface VacancyLayerComponentDependencies {
    fun getVacancyRepository(): VacancyRepository
}