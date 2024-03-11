package com.feature.resume.ui.models

import androidx.compose.ui.graphics.vector.ImageVector

enum class TabContentType {
    COMMON,
    EDUCATION,
    JOB_EXPERIENCE;

    override fun toString(): String =
        name.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " ")

}

data class TabItem(
    val contentType: TabContentType,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)