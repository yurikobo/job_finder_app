package com.example.kotlincourse.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
enum class Relocation {
    @SerialName("preferable")
    PREFERABLE,

    @SerialName("possible")
    POSSIBLE,

    @SerialName("is not possible")
    IS_NOT_POSSIBLE;

    override fun toString(): String = name.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " ")
}

object Relocations : Table() {

    val id = long("id").autoIncrement()
    val relocationType = varchar("relocation_type", 50)

    override val primaryKey = PrimaryKey(id)


}