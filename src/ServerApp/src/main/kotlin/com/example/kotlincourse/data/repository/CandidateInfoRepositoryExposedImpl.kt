package com.example.kotlincourse.data.repository

import com.example.kotlincourse.dao.DatabaseSingleton.dbQuery
import com.example.kotlincourse.data.models.*
import com.example.kotlincourse.domain.repository.CandidateInfoRepository
import com.example.kotlincourse.domain.repository.ContactRepository
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.kotlin.datetime.dateParam
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.select
import javax.inject.Inject

class CandidateInfoRepositoryExposedImpl @Inject constructor(private val contactRepository: ContactRepository) :
    CandidateInfoRepository {

    private fun resultRowToCandidateInfo(row: ResultRow) = CandidateInfo(
        id = row[CandidatesInfo.id],
        name = row[CandidatesInfo.name],
        profession = Profession.valueOf(row[Professions.name]),
        gender = Gender.valueOf(row[Genders.gender]),
        birthDate = row[CandidatesInfo.birthDate].toJavaLocalDate(),
        contact = Contact(phone = "", email = ""),
        relocation = Relocation.valueOf(row[Relocations.relocationType])
    )


    override suspend fun findById(id: Long): CandidateInfo? = dbQuery {
        CandidatesInfo
            .leftJoin(Professions, { CandidatesInfo.professionId }, { Professions.id })
            .leftJoin(Genders, { CandidatesInfo.genderId }, { Genders.id })
            .leftJoin(Relocations, { CandidatesInfo.relocationId }, { Relocations.id })
            .select { CandidatesInfo.id eq id }
            .map(::resultRowToCandidateInfo)
            .singleOrNull()
            ?.let { candidateInfo ->
                contactRepository.findByCandidateInfoId(candidateInfo.id)?.let { contact ->
                    candidateInfo.copy(contact = contact)
                }
            }
    }

    override suspend fun findByResumeId(id: Long): CandidateInfo? = dbQuery {
        CandidatesInfo
            .leftJoin(Professions, { CandidatesInfo.professionId }, { Professions.id })
            .leftJoin(Genders, { CandidatesInfo.genderId }, { Genders.id })
            .leftJoin(Relocations, { CandidatesInfo.relocationId }, { Relocations.id })
            .select { CandidatesInfo.resumeId eq id }
            .map(::resultRowToCandidateInfo)
            .singleOrNull()
            ?.let { candidateInfo ->
                contactRepository.findByCandidateInfoId(candidateInfo.id)?.let { contact ->
                    candidateInfo.copy(contact = contact)
                }
            }
    }

    override suspend fun save(entity: CandidateInfo) {
        dbQuery {
            CandidatesInfo.insert {
                it[name] = entity.name
                it[professionId] = professionId
                it[genderId] = genderId
                it[birthDate] = dateParam(entity.birthDate.toKotlinLocalDate())
                it[resumeId] = resumeId
                it[relocationId] = relocationId
            }
        }
    }

    override suspend fun update(entity: CandidateInfo) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(entity: CandidateInfo) {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(): List<CandidateInfo> {
        TODO("Not yet implemented")
    }
}