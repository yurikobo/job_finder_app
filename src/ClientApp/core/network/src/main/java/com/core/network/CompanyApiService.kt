package com.core.network

import com.core.network.models.CompanyDTO
import com.core.network.models.CompanyInfoDTO
import retrofit2.http.GET
import retrofit2.http.Path


interface CompanyApiService {

    @GET("companies")
    suspend fun getCompaniesInfo(): List<CompanyInfoDTO>

    @GET("companies/{id}")
    suspend fun getCompanyDetails(@Path("id") id: Long): CompanyDTO?


}