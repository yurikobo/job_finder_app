package com.example.kotlincourse.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
enum class Gender {
    @SerialName("male")
    MALE,

    @SerialName("female")
    FEMALE;

    override fun toString(): String = name.lowercase().replaceFirstChar { it.uppercase() }
}

object Genders : Table() {
    val id = long("id").autoIncrement()
    val gender = varchar("gender_type", 50)

    override val primaryKey = PrimaryKey(id)

}