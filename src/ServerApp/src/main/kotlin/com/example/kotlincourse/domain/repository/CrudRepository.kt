package com.example.kotlincourse.domain.repository

interface CrudRepository<T> {
    suspend fun findById(id: Long): T?
    suspend fun save(entity: T)
    suspend fun update(entity: T)
    suspend fun delete(entity: T)
    suspend fun findAll(): List<T>
}