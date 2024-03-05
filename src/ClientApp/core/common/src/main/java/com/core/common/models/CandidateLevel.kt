package com.core.common.models

enum class CandidateLevel(val seniorityRange: ClosedRange<Int>) : ResumeTag {
    JUNIOR(0 until 3),

    MIDDLE(3 until 6),

    SENIOR(6..Int.MAX_VALUE);

    override fun toString(): String =
        name.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " ")

}