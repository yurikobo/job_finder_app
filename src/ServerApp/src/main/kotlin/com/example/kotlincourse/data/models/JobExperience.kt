package com.example.kotlincourse.data.models

import com.example.kotlincourse.data.adapters.YearMonthAdapter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date
import java.time.YearMonth

@Serializable
data class JobExperience(
    @Serializable(YearMonthAdapter::class)
    @SerialName("date_start")
    val dateStart: YearMonth,
    @Serializable(YearMonthAdapter::class)
    @SerialName("date_end")
    val dateEnd: YearMonth,
    @SerialName("company_name")
    val companyName: String,
    @SerialName("description")
    val description: String
)

object JobExperiences : Table() {
    val id = long("id").autoIncrement()
    val resumeId = long("resume_id")
        .references(Resumes.id)
    val dateStart = date("start_date")
    val dateEnd = date("end_date")
    val companyName = varchar("company_name", 50)
    val description = varchar("description", 255)

    override val primaryKey = PrimaryKey(id)
}