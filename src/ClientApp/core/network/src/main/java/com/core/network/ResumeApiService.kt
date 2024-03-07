package com.core.network

import com.core.common.models.Resume
import com.core.common.models.ResumeWithTags
import retrofit2.http.GET
import retrofit2.http.Path


interface ResumeApiService {

    @GET("resumes")
    suspend fun getResumesList(): List<Resume>

    @GET("resumes/{id}")
    suspend fun getResumeWithTags(@Path("id") id: Long): ResumeWithTags?
}