package com.example.kotlincourse.data

import com.example.kotlincourse.data.models.*
import com.example.kotlincourse.data.models.EducationType.HIGHER
import com.example.kotlincourse.data.models.EducationType.SECONDARY_SPECIAL
import com.example.kotlincourse.data.models.Gender.FEMALE
import com.example.kotlincourse.data.models.Gender.MALE
import com.example.kotlincourse.data.models.Profession.DEVELOPER
import com.example.kotlincourse.data.models.Profession.QA
import com.example.kotlincourse.data.models.Relocation.IS_NOT_POSSIBLE
import com.example.kotlincourse.data.models.Relocation.POSSIBLE
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth

object ResumeData {
    val resumeList = listOf(
        Resume(
            id = 1,
            candidateInfo = CandidateInfo(
                name = "First candidate name",
                profession = QA,
                gender = MALE,
                birthDate = LocalDate.of(1968, 1, 1),
                contact = Contact(
                    phone = "89251111111",
                    email = "email@email.com"
                ),
                relocation = IS_NOT_POSSIBLE

            ),
            educationList = listOf(
                Education(
                    type = SECONDARY_SPECIAL,
                    startYear = Year.of(2020),
                    endYear = Year.of(2021),
                    description = "Secondary special ed"
                ), Education(
                    type = HIGHER,
                    startYear = Year.of(2021),
                    endYear = Year.of(2022),
                    description = "Higher ed"
                )
            ),
            jobExperienceList = listOf(
                JobExperience(
                    dateStart = YearMonth.of(2022, 2),
                    dateEnd = YearMonth.of(2022, 3),
                    companyName = "First company name",
                    description = "First job experience"
                ),
                JobExperience(
                    dateStart = YearMonth.of(2022, 3),
                    dateEnd = YearMonth.of(2022, 4),
                    companyName = "Second company name",
                    description = "Second job experience"
                )
            ),
            freeForm = "First CV"

        ), Resume(
            id = 2,
            candidateInfo = CandidateInfo(
                name = "Second candidate name",
                profession = DEVELOPER,
                gender = FEMALE,
                birthDate = LocalDate.of(1986, 2, 2),
                contact = Contact(
                    phone = "85552222222",
                    email = "gmail@gmail.com"
                ),
                relocation = POSSIBLE

            ),
            educationList = listOf(
                Education(
                    type = SECONDARY_SPECIAL,
                    startYear = Year.of(2020),
                    endYear = Year.of(2021),
                    description = "Secondary special ed"
                ), Education(
                    type = HIGHER,
                    startYear = Year.of(2021),
                    endYear = Year.of(2022),
                    description = "Higher ed"
                )
            ),
            jobExperienceList = listOf(
                JobExperience(
                    dateStart = YearMonth.of(2022, 2),
                    dateEnd = YearMonth.of(2022, 3),
                    companyName = "First company name",
                    description = "First job experience"
                ),
                JobExperience(
                    dateStart = YearMonth.of(2022, 3),
                    dateEnd = YearMonth.of(2022, 4),
                    companyName = "Second company name",
                    description = "Second job experience"
                )
            ),
            freeForm = "Second CV"

        )
    )


}