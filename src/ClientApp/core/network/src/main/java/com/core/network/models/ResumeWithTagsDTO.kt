package com.core.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeWithTagsDTO(
    @SerialName("resume")
    val resume: ResumeResponse,
    @SerialName("tag_list")
    val tagList: List<ResumeTagResponse>
)
