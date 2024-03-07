package com.core.common.models

import com.core.common.adapters.YearAdapter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Year

@Serializable
data class Education(
    @SerialName("type")
    val type: EducationType,
    @Serializable(YearAdapter::class)
    @SerialName("year_start")
    val startYear: Year,
    @Serializable(YearAdapter::class)
    @SerialName("year_end")
    val endYear: Year,
    @SerialName("description")
    val description: String
)

