package com.feature.company.domain.models

enum class Profession {
    DEVELOPER,

    QA,

    PROJECT_MANAGER,

    ANALYST,

    DESIGNER;

    override fun toString(): String =
        name.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " ")
}