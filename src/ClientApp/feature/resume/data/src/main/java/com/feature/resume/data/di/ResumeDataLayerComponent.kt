package com.feature.resume.data.di

import com.core.network.di.NetworkModule
import com.feature.resume.domain.di.ResumeLayerComponentDependencies
import com.feature.resume.domain.repositories.ResumeRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ResumeDataLayerModule::class, NetworkModule::class]
)
interface ResumeDataLayerComponent : ResumeLayerComponentDependencies {
    
    override fun getResumeRepository(): ResumeRepository

}