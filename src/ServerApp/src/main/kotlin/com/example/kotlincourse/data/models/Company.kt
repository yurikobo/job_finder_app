package com.example.kotlincourse.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Company(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("field_of_activity") val fieldOfActivity: FieldOfActivity,
    @SerialName("vacancies") val listOfVacancies: List<Vacancy>,
    @SerialName("contacts") val contact: String
)

object Companies: Table(){
    val id = long("id").autoIncrement()
    val name = varchar("name", 255)
    val contact = varchar("contact", length = 255)
    val fieldOfActivity = long("field_of_activity_id")
        .references(FieldsOfActivity.id)


    override val primaryKey = PrimaryKey(id)
}

