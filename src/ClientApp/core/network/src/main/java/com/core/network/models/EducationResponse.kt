package com.core.network.models

import com.core.network.adapters.YearAdapter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Year

@kotlinx.serialization.Serializable
data class EducationResponse(
    @SerialName("type")
    val type: EducationTypeResponse,
    @kotlinx.serialization.Serializable(YearAdapter::class)
    @SerialName("year_start")
    val startYear: Year,
    @Serializable(YearAdapter::class)
    @SerialName("year_end")
    val endYear: Year,
    @SerialName("description")
    val description: String
)

