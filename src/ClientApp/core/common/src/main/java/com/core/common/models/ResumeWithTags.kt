package com.core.common.models

data class ResumeWithTags(
    val resume: Resume,
    val tagList: List<ResumeTag>
)
