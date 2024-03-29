package com.core.common.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Profession : ResumeTag {
    @SerialName("developer")
    DEVELOPER,

    @SerialName("qa")
    QA,

    @SerialName("pm")
    PROJECT_MANAGER,

    @SerialName("analyst")
    ANALYST,

    @SerialName("designer")
    DESIGNER;

    override fun toString(): String =
        name.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " ")
}