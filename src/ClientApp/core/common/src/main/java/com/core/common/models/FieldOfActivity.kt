package com.core.common.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class FieldOfActivity {
    @SerialName("IT")
    IT,

    @SerialName("banking")
    BANKING,

    @SerialName("public services")
    PUBLIC_SERVICES;

    override fun toString(): String =
        name.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " ")
}