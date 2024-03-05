package com.core.common.models

enum class Profession : ResumeTag {
    DEVELOPER,

    QA,

    PROJECT_MANAGER,

    ANALYST,

    DESIGNER;

    override fun toString(): String =
        name.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " ")
}