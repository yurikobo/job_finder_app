package com.core.common.models


enum class FieldOfActivity {
    IT,

    BANKING,

    PUBLIC_SERVICES;

    override fun toString(): String =
        name.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " ")
}