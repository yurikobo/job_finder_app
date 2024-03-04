package com.example.kotlincourse.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeWithTags(
    @SerialName("resume")
    val resume: Resume,
    @SerialName("tag_list")
    val tagList: List<ResumeTag>
)
