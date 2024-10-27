package com.ritika.taskapi.dataclasses

data class SubmissionResponse(
    val count: Int,
    val submission: List<Title>
)