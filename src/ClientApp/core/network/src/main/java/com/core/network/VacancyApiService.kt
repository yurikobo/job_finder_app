package com.core.network

import com.core.common.models.Vacancy
import com.core.common.models.VacancyInfo
import retrofit2.http.GET
import retrofit2.http.Path


interface VacancyApiService {

    @GET("vacancies")
    suspend fun getVacanciesInfo(): List<VacancyInfo>

    @GET("vacancies/{id}")
    suspend fun getVacancyDetails(@Path("id") id: Long): Vacancy?
}