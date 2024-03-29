package com.example.kotlincourse.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
enum class EducationType {
    @SerialName("higher")
    HIGHER,

    @SerialName("secondary")
    SECONDARY,

    @SerialName("secondary special")
    SECONDARY_SPECIAL;

    override fun toString(): String = name.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " ")
}

object EducationTypes : Table() {
    val id = long("id").autoIncrement()
    val educationType = varchar("education_type", 50)

    override val primaryKey = PrimaryKey(id)

}