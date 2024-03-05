package com.core.common.models

import java.time.Year

data class Education(
    val type: EducationType,
    val startYear: Year,
    val endYear: Year,
    val description: String
)

