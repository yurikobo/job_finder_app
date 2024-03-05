package com.core.common.navigation

enum class NavigationConstants(
    val nestedRoute: String,
    val screenRoute: String
) {
    HOME_SCREEN(
        nestedRoute = "Main menu",
        screenRoute = "home_screen_route"
    ),
    COMPANY_DETAILS_SCREEN(
        nestedRoute = "Company details",
        screenRoute = "company_details_screen_route"
    ),
    VACANCY_DETAILS_SCREEN(
        nestedRoute = "Vacancy details",
        screenRoute = "vacancy_screen_route"
    ),
    RESUME_SCREEN(
        nestedRoute = "Resume",
        screenRoute = "resume_screen_route"
    )
}




