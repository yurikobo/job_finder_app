package com.core.common.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CompanyInfo(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("field_of_activity") val fieldOfActivity: FieldOfActivity
)