package com.core.network

import com.core.common.models.Company
import com.core.common.models.CompanyInfo
import retrofit2.http.GET
import retrofit2.http.Path


interface CompanyApiService {

    @GET("companies")
    suspend fun getCompaniesInfo(): List<CompanyInfo>

    @GET("companies/{id}")
    suspend fun getCompanyDetails(@Path("id") id: Long): Company?


}