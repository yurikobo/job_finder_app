package com.example.kotlincourse.data.models

import com.example.kotlincourse.data.adapters.YearAdapter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date
import java.time.Year

@Serializable
data class Education(
    @SerialName("type")
    val type: EducationType,
    @Serializable(YearAdapter::class)
    @SerialName("year_start")
    val startYear: Year,
    @Serializable(YearAdapter::class)
    @SerialName("year_end")
    val endYear: Year,
    @SerialName("description")
    val description: String
)

object Educations : Table() {
    val id = long("id").autoIncrement()
    val resumeId = long("resume_id")
        .references(Resumes.id)
    val educationTypeId = long("education_type_id")
        .references(EducationTypes.id)
    val startYear = date("start_year")
    val endYear = date("end_year")
    val description = varchar("description", 255)

    override val primaryKey = PrimaryKey(id)
}

