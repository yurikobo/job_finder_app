package com.feature.resume.ui.di

import com.feature.resume.ui.navigation.ResumeApi
import com.feature.resume.ui.navigation.ResumeApiImpl
import com.feature.resume.ui.screen.ResumeViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ResumeUiModule {

    @Provides
    @Singleton
    fun provideResumeApi(resumeViewModel: ResumeViewModel): ResumeApi {
        return ResumeApiImpl(resumeViewModel)
    }
}