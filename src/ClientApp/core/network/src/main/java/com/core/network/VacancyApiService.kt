package com.core.network

import com.core.network.models.VacancyDTO
import com.core.network.models.VacancyInfoDTO
import retrofit2.http.GET
import retrofit2.http.Path


interface VacancyApiService {

    @GET("vacancies")
    suspend fun getVacanciesInfo(): List<VacancyInfoDTO>

    @GET("vacancies/{id}")
    suspend fun getVacancyDetails(@Path("id") id: Long): VacancyDTO?
}