package com.example.kotlincourse.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Contact(
    @SerialName("phone")
    val phone: String,
    @SerialName("email")
    val email: String
)

object Contacts : Table() {
    val id = long("id").autoIncrement()
    val candidateId = long("candidate_id")
        .references(CandidatesInfo.id)
    val phone = varchar("phone", 25)
    val email = varchar("email", 100)

    override val primaryKey = PrimaryKey(id)
}