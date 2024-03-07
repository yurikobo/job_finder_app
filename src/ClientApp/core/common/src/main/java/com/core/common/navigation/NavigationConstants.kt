package com.core.common.navigation

enum class NavigationConstants(
    val nestedRoute: String,
    val screenRoute: String
) {
    HOME_SCREEN(
        nestedRoute = "Main menu screen",
        screenRoute = "Main menu"
    ),
    COMPANY_DETAILS_SCREEN(
        nestedRoute = "Company screen",
        screenRoute = "Company details"
    ),
    VACANCY_DETAILS_SCREEN(
        nestedRoute = "Vacancy screen",
        screenRoute = "Vacancy details"
    ),
    RESUME_SCREEN(
        nestedRoute = "Resume screen",
        screenRoute = "Resume"
    )
}




