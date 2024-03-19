package com.example.kotlincourse.dao

import com.example.kotlincourse.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.LocalDate
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.kotlin.datetime.dateParam
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseSingleton {
    fun init() {
        val driverClassName = "org.h2.Driver"
        val jdbcURL = "jdbc:h2:file:./build/db"
        val database = Database.connect(jdbcURL, driverClassName)
        transaction(database) {
            addLogger(StdOutSqlLogger)


            SchemaUtils.drop(
                FieldsOfActivity, Companies, Professions, CandidateLevels, Vacancies,
                Genders, Relocations, EducationTypes, Educations, JobExperiences, Resumes, CandidatesInfo, Contacts
            )



            SchemaUtils.create(FieldsOfActivity)
            val itId = FieldsOfActivity.insert {
                it[name] = FieldOfActivity.IT.name
            } get FieldsOfActivity.id
            val bankingId = FieldsOfActivity.insert {
                it[name] = FieldOfActivity.BANKING.name
            } get FieldsOfActivity.id
            val publicServicesId = FieldsOfActivity.insert {
                it[name] = FieldOfActivity.PUBLIC_SERVICES.name
            } get FieldsOfActivity.id



            SchemaUtils.create(Companies)
            val firstCompany = Companies.insert {
                it[name] = "My company"
                it[fieldOfActivity] = itId
                it[contact] = "88005553535"
            } get Companies.id
            val secondCompany = Companies.insert {
                it[name] = "My second company"
                it[fieldOfActivity] = bankingId
                it[contact] = "123456"
            } get Companies.id
            Companies.insert {
                it[name] = "My third company"
                it[fieldOfActivity] = publicServicesId
                it[contact] = "8888888888"
            }



            SchemaUtils.create(Professions)
            val developerId = Professions.insert {
                it[name] = Profession.DEVELOPER.name
            } get Professions.id
            val qaId = Professions.insert {
                it[name] = Profession.QA.name
            } get Professions.id
            val pmId = Professions.insert {
                it[name] = Profession.PROJECT_MANAGER.name
            } get Professions.id
            val analystId = Professions.insert {
                it[name] = Profession.ANALYST.name
            } get Professions.id
            val designerId = Professions.insert {
                it[name] = Profession.DESIGNER.name
            } get Professions.id



            SchemaUtils.create(CandidateLevels)
            val juniorId = CandidateLevels.insert {
                it[name] = CandidateLevel.JUNIOR.name
            } get CandidateLevels.id
            val middleId = CandidateLevels.insert {
                it[name] = CandidateLevel.MIDDLE.name
            } get CandidateLevels.id
            val seniorId = CandidateLevels.insert {
                it[name] = CandidateLevel.SENIOR.name
            } get CandidateLevels.id



            SchemaUtils.create(Vacancies)
            Vacancies.insert {
                it[companyId] = firstCompany
                it[professionId] = developerId
                it[levelId] = juniorId
                it[salary] = 100
                it[description] = "Dev junior vacancy example"
            }
            Vacancies.insert {
                it[companyId] = firstCompany
                it[professionId] = developerId
                it[levelId] = middleId
                it[salary] = 200
                it[description] = "Dev middle vacancy example"
            }
            Vacancies.insert {
                it[companyId] = firstCompany
                it[professionId] = developerId
                it[levelId] = seniorId
                it[salary] = 300
                it[description] = "Dev senior vacancy example"
            }
            Vacancies.insert {
                it[professionId] = designerId
                it[levelId] = juniorId
                it[companyId] = secondCompany
                it[salary] = 100
                it[description] = "Designer junior vacancy example"
            }
            Vacancies.insert {
                it[professionId] = analystId
                it[levelId] = middleId
                it[companyId] = secondCompany
                it[salary] = 200
                it[description] = "Analyst middle vacancy example"
            }
            Vacancies.insert {
                it[professionId] = qaId
                it[levelId] = seniorId
                it[companyId] = secondCompany
                it[salary] = 300
                it[description] = "QA senior vacancy example"
            }
            Vacancies.insert {
                it[professionId] = pmId
                it[levelId] = seniorId
                it[companyId] = secondCompany
                it[salary] = 300
                it[description] = "PM senior vacancy example"
            }



            SchemaUtils.create(Genders)
            val maleId = Genders.insert {
                it[gender] = Gender.MALE.name
            } get Genders.id
            val femaleId = Genders.insert {
                it[gender] = Gender.FEMALE.name
            } get Genders.id


            SchemaUtils.create(Relocations)
            val relocationPossibleId = Relocations.insert {
                it[relocationType] = Relocation.POSSIBLE.name
            } get Relocations.id
            val relocationIsNotPossibleId = Relocations.insert {
                it[relocationType] = Relocation.IS_NOT_POSSIBLE.name
            } get Relocations.id
            Relocations.insert {
                it[relocationType] = Relocation.PREFERABLE.name
            }


            SchemaUtils.create(EducationTypes)
            val higherEdId = EducationTypes.insert {
                it[educationType] = EducationType.HIGHER.name
            } get EducationTypes.id
            val secondaryEdId = EducationTypes.insert {
                it[educationType] = EducationType.SECONDARY.name
            } get EducationTypes.id
            EducationTypes.insert {
                it[educationType] = EducationType.SECONDARY_SPECIAL.name
            }


            SchemaUtils.create(Resumes)
            val firstCVId = Resumes.insert {
                it[freeForm] = "First CV"
            } get Resumes.id
            val secondCVId = Resumes.insert {
                it[freeForm] = "Second CV"
            } get Resumes.id



            SchemaUtils.create(CandidatesInfo)
            val firstCandidateId = CandidatesInfo.insert {
                it[name] = "First candidate name"
                it[professionId] = qaId
                it[genderId] = maleId
                it[birthDate] = dateParam(LocalDate(1968, 1, 1))
                it[resumeId] = firstCVId
                it[relocationId] = relocationIsNotPossibleId
            } get CandidatesInfo.id
            val secondCandidateId = CandidatesInfo.insert {
                it[name] = "Second candidate name"
                it[professionId] = developerId
                it[genderId] = femaleId
                it[birthDate] = dateParam(LocalDate(1986, 2, 2))
                it[resumeId] = secondCVId
                it[relocationId] = relocationPossibleId
            } get CandidatesInfo.id



            SchemaUtils.create(Contacts)
            Contacts.insert {
                it[candidateId] = firstCandidateId
                it[phone] = "89251111111"
                it[email] = "email@email.com"
            }
            Contacts.insert {
                it[candidateId] = secondCandidateId
                it[phone] = "85552222222"
                it[email] = "gmail@gmail.com"
            }



            SchemaUtils.create(Educations)
            Educations.insert {
                it[resumeId] = firstCVId
                it[educationTypeId] = secondaryEdId
                it[startYear] = dateParam(LocalDate(2020, 1, 1))
                it[endYear] = dateParam(LocalDate(2021, 1, 1))
                it[description] = "Secondary special ed"
            }
            Educations.insert {
                it[resumeId] = firstCVId
                it[educationTypeId] = higherEdId
                it[startYear] = dateParam(LocalDate(2021, 1, 1))
                it[endYear] = dateParam(LocalDate(2022, 1, 1))
                it[description] = "Higher ed"
            }
            Educations.insert {
                it[resumeId] = secondCVId
                it[educationTypeId] = secondaryEdId
                it[startYear] = dateParam(LocalDate(2020, 1, 1))
                it[endYear] = dateParam(LocalDate(2021, 1, 1))
                it[description] = "Secondary special ed"
            }
            Educations.insert {
                it[resumeId] = secondCVId
                it[educationTypeId] = higherEdId
                it[startYear] = dateParam(LocalDate(2021, 1, 1))
                it[endYear] = dateParam(LocalDate(2022, 1, 1))
                it[description] = "Higher ed"
            }



            SchemaUtils.create(JobExperiences)
            JobExperiences.insert {
                it[resumeId] = firstCVId
                it[dateStart] = dateParam(LocalDate(2022, 2, 1))
                it[dateEnd] = dateParam(LocalDate(2022, 3, 1))
                it[companyName] = "First company name"
                it[description] = "First job experience"
            }
            JobExperiences.insert {
                it[resumeId] = firstCVId
                it[dateStart] = dateParam(LocalDate(2022, 3, 1))
                it[dateEnd] = dateParam(LocalDate(2022, 4, 1))
                it[companyName] = "Second company name"
                it[description] = "Second job experience"
            }
            JobExperiences.insert {
                it[resumeId] = secondCVId
                it[dateStart] = dateParam(LocalDate(2022, 2, 1))
                it[dateEnd] = dateParam(LocalDate(2022, 3, 1))
                it[companyName] = "First company name"
                it[description] = "First job experience"
            }
            JobExperiences.insert {
                it[resumeId] = secondCVId
                it[dateStart] = dateParam(LocalDate(2022, 3, 1))
                it[dateEnd] = dateParam(LocalDate(2022, 4, 1))
                it[companyName] = "Second company name"
                it[description] = "Second job experience"
            }
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
}