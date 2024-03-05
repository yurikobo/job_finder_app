package com.core.common.models


enum class EducationType {
    HIGHER,

    SECONDARY,

    SECONDARY_SPECIAL;

    override fun toString(): String =
        name.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " ")
}