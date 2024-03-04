package com.example.kotlincourse.data.repository

import com.example.kotlincourse.data.ResumeData
import com.example.kotlincourse.data.models.Resume
import com.example.kotlincourse.domain.repository.ResumeRepository

class ResumeRepositoryImpl : ResumeRepository {

    override fun findById(id: Long): Resume? = ResumeData.resumeList.find { it.id == id }

    override fun save(entity: Resume) {
        TODO("Not yet implemented")
    }

    override fun update(entity: Resume) {
        println(entity)
    }

    override fun delete(entity: Resume) {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Resume> {
        return ResumeData.resumeList
    }
}