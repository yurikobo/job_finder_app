package com.core.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContactResponse(
    @SerialName("phone")
    val phone: String,
    @SerialName("email")
    val email: String
)