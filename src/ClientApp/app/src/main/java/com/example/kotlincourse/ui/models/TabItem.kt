package com.example.kotlincourse.ui.models

import androidx.compose.ui.graphics.vector.ImageVector

enum class TabContentType {
    COMPANIES,
    VACANCIES;

    override fun toString(): String = name.lowercase().replaceFirstChar { it.uppercase() }
}

data class TabItem(
    val contentType: TabContentType,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)