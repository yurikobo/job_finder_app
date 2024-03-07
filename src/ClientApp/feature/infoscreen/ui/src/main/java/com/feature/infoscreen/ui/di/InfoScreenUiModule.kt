package com.feature.infoscreen.ui.di


import com.feature.infoscreen.ui.navigation.InfoScreenApi
import com.feature.infoscreen.ui.navigation.InfoScreenApiImpl
import com.feature.infoscreen.ui.screen.InfoScreenViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object InfoScreenUiModule {

    @Provides
    @Singleton
    fun provideInfoScreenApi(infoScreenViewModel: InfoScreenViewModel):InfoScreenApi {
        return InfoScreenApiImpl(infoScreenViewModel)
    }
}