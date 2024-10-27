
package com.ritika.taskapi.dataclasses

data class SolvedResponse(
    val totalSolved: Int,
    val problems: List<SolvedProblem>
)

