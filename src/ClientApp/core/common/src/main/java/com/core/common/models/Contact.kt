package com.core.common.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    @SerialName("phone")
    val phone: String,
    @SerialName("email")
    val email: String
)