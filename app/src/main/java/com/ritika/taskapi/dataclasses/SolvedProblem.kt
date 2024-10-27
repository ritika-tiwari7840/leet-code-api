package com.ritika.taskapi.dataclasses

data class SolvedProblem(
    val id: String?,
    val title: String?,
    val difficulty: String?,
    val solvedAt: String?,
    val tags: List<String>?
)