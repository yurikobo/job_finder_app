package com.core.network

import com.core.network.models.ResumeResponse
import com.core.network.models.ResumeWithTagsDTO
import retrofit2.http.GET
import retrofit2.http.Path


interface ResumeApiService {

    @GET("resumes")
    suspend fun getResumesList(): List<ResumeResponse>

    @GET("resumes/{id}")
    suspend fun getResumeWithTags(@Path("id") id: Long): ResumeWithTagsDTO?
}