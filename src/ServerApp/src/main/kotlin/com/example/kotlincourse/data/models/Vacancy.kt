package com.example.kotlincourse.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Vacancy(
    @SerialName("id") val id: Long,
    @SerialName("profession") val profession: Profession,
    @SerialName("level") val level: CandidateLevel,
    @SerialName("salary") val salary: Int,
    @SerialName("description") val description: String
)

object Vacancies: Table(){
    val id = long("id").autoIncrement()
    val companyId = long("company_id")
        .references(Companies.id)
    val professionId = long("profession_id")
        .references(Professions.id)
    val levelId = long("level_id")
        .references(CandidateLevels.id)
    val salary = integer("salary_level")
    val description = varchar("vacancy_description", 1024)

    override val primaryKey = PrimaryKey(id)

}

