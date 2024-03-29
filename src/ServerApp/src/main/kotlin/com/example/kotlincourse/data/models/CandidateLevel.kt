package com.example.kotlincourse.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
enum class CandidateLevel(val seniorityRange: ClosedRange<Int>) : ResumeTag {
    @SerialName("junior")
    JUNIOR(0 until 3),

    @SerialName("middle")
    MIDDLE(3 until 6),

    @SerialName("senior")
    SENIOR(6..Int.MAX_VALUE);

    override fun toString(): String = name.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " ")

}

object CandidateLevels: Table(){
    val id = long("id").autoIncrement()
    val name = varchar("name", 255)

    override val primaryKey = PrimaryKey(id)

}