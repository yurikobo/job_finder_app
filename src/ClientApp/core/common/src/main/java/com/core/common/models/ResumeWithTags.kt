package com.core.common.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeWithTags(
    @SerialName("resume")
    val resume: Resume,
    @SerialName("tag_list")
    val tagList: List<List<String>>
)
