package com.example.kotlincourse.domain.repository

import com.example.kotlincourse.data.models.CandidateInfo

interface CandidateInfoRepository : CrudRepository<CandidateInfo> {
    suspend fun findByResumeId(id: Long): CandidateInfo?
}