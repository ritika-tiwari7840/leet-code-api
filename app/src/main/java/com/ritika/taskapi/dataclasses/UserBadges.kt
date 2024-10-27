package com.ritika.taskapi.dataclasses

data class UserBadges(
    val activeBadge: Any?,
    val badges: List<Badge>?,
    val badgesCount: Int?,
    val upcomingBadges: List<UpcomingBadge>?
)