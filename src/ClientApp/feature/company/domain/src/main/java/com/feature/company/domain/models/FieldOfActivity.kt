package com.feature.company.domain.models


enum class FieldOfActivity {
    IT,

    BANKING,

    PUBLIC_SERVICES;

    override fun toString(): String =
        name.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " ")
}