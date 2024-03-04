package com.example.kotlincourse.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class EducationType {
    @SerialName("higher")
    HIGHER,

    @SerialName("secondary")
    SECONDARY,

    @SerialName("secondary special")
    SECONDARY_SPECIAL;

    override fun toString(): String = name.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " ")
}