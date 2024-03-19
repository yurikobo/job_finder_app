package com.example.kotlincourse.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
enum class FieldOfActivity {
    @SerialName("IT")
    IT,

    @SerialName("banking")
    BANKING,

    @SerialName("public services")
    PUBLIC_SERVICES;

    override fun toString(): String = name.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " ")
}

object FieldsOfActivity : Table() {
    val id = long("id").autoIncrement()
    val name = varchar("name", 255)

    override val primaryKey = PrimaryKey(id)

}