package com.example.kotlincourse.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
enum class Profession : ResumeTag {
    @SerialName("developer")
    DEVELOPER,

    @SerialName("qa")
    QA,

    @SerialName("pm")
    PROJECT_MANAGER,

    @SerialName("analyst")
    ANALYST,

    @SerialName("designer")
    DESIGNER;

    override fun toString(): String = name.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " ")
}

object Professions: Table(){
    val id = long("id").autoIncrement()
    val name = varchar("name", 50)

    override val primaryKey = PrimaryKey(id)

}