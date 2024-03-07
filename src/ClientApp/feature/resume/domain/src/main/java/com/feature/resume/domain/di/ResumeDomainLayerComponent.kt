package com.feature.resume.domain.di

import com.feature.resume.domain.usecases.GetResumeUseCase
import com.feature.resume.domain.usecases.UpdateResumeUseCase
import dagger.Component

@Component(
    dependencies = [ResumeLayerComponentDependencies::class]
)
interface ResumeDomainLayerComponent {
    val getResumeUseCase: GetResumeUseCase
    val updateResumeUseCase: UpdateResumeUseCase
}