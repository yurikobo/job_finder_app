package com.example.kotlincourse.domain.repository

import com.example.kotlincourse.data.models.Contact

interface ContactRepository : CrudRepository<Contact> {
    suspend fun findByCandidateInfoId(id: Long): Contact?
}