package com.example.kotlincourse

import com.example.kotlincourse.data.models.CandidateLevel.*
import com.example.kotlincourse.data.models.Company
import com.example.kotlincourse.data.models.FieldOfActivity.*
import com.example.kotlincourse.data.models.Profession.*
import com.example.kotlincourse.data.models.Vacancy

object CompanyData {
    val companyList = listOf(
        Company(
            id = 1,
            name = "My company",
            fieldOfActivity = IT,
            listOfVacancies = listOf(
                Vacancy(
                    id = 1,
                    profession = DEVELOPER,
                    level = JUNIOR,
                    salary = 100,
                    description = "Dev junior vacancy example"
                ),
                Vacancy(
                    id = 2,
                    profession = DEVELOPER,
                    level = MIDDLE,
                    salary = 200,
                    description = "Dev middle vacancy example"
                ),
                Vacancy(
                    id = 3,
                    profession = DEVELOPER,
                    level = SENIOR,
                    salary = 300,
                    description = "Dev senior vacancy example"
                )
            ),
            contact = "88005553535"
        ),
        Company(
            id = 2,
            name = "My second company",
            fieldOfActivity = BANKING,
            listOfVacancies = listOf(
                Vacancy(
                    id = 4,
                    profession = DESIGNER,
                    level = JUNIOR,
                    salary = 100,
                    description = "Designer junior vacancy example"
                ),
                Vacancy(
                    id = 5,
                    profession = ANALYST,
                    level = MIDDLE,
                    salary = 200,
                    description = "Analyst middle vacancy example"
                ),
                Vacancy(
                    id = 6,
                    profession = QA,
                    level = SENIOR,
                    salary = 300,
                    description = "QA senior vacancy example"
                ),
                Vacancy(
                    id = 7,
                    profession = PROJECT_MANAGER,
                    level = SENIOR,
                    salary = 300,
                    description = "PM senior vacancy example"
                )
            ),
            contact = "123456"
        ),
        Company(
            id = 3,
            name = "My third company",
            fieldOfActivity = PUBLIC_SERVICES,
            listOfVacancies = emptyList(),
            contact = "8888888888"
        )
    )
}