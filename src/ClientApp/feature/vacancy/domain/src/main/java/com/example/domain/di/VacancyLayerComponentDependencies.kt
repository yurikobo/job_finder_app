package com.example.domain.di

import com.example.domain.repositories.VacancyRepository

interface VacancyLayerComponentDependencies {
    fun getVacancyRepository(): VacancyRepository
}