package com.feature.resume.domain.di

import com.feature.resume.domain.repositories.ResumeRepository

interface ResumeLayerComponentDependencies {
    fun getResumeRepository(): ResumeRepository
}