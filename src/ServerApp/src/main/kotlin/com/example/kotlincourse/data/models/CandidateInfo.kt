package com.example.kotlincourse.data.models

import com.example.kotlincourse.data.adapters.LocalDateAdapter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date
import java.time.LocalDate

@Serializable
data class CandidateInfo(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("profession")
    val profession: Profession,
    @SerialName("sex")
    val gender: Gender,
    @Serializable(LocalDateAdapter::class)
    @SerialName("birth_date")
    val birthDate: LocalDate,
    @SerialName("contacts")
    val contact: Contact,
    @SerialName("relocation")
    val relocation: Relocation
)

object CandidatesInfo : Table() {
    val id = long("id").autoIncrement()
    val resumeId = long("resume_id").references(Resumes.id)
    val name = varchar("candidate_name", 255)
    val professionId = long("profession_id").references(Professions.id)
    val genderId = long("gender_id").references(Genders.id)
    val birthDate = date("birth_date")
    val relocationId = long("relocation_id").references(Relocations.id)

    override val primaryKey = PrimaryKey(id)
}




