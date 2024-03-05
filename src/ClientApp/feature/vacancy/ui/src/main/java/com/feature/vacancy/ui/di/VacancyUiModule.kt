package com.feature.vacancy.ui.di

import com.feature.vacancy.ui.navigation.VacancyApi
import com.feature.vacancy.ui.navigation.VacancyApiImpl
import com.feature.vacancy.ui.screen.VacancyViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object VacancyUiModule {

    @Provides
    @Singleton
    fun provideCompanyApi(vacancyViewModel: VacancyViewModel): VacancyApi {
        return VacancyApiImpl(vacancyViewModel)
    }
}