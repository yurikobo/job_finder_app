package com.core.network.models

import com.core.network.adapters.YearMonthAdapter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.YearMonth

@kotlinx.serialization.Serializable
data class JobExperienceResponse(
    @kotlinx.serialization.Serializable(YearMonthAdapter::class)
    @SerialName("date_start")
    val dateStart: YearMonth,
    @Serializable(YearMonthAdapter::class)
    @SerialName("date_end")
    val dateEnd: YearMonth,
    @SerialName("company_name")
    val companyName: String,
    @SerialName("description")
    val description: String
)