package com.example.kotlincourse.domain.repository

interface CrudRepository<T> {
    fun findById(id: Long): T?
    fun save(entity: T)
    fun update(entity: T)
    fun delete(entity: T)
    fun findAll(): List<T>
}