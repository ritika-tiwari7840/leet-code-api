package com.ritika.taskapi
import com.ritika.taskapi.dataclasses.UserBadges
import com.ritika.taskapi.dataclasses.UserProfile
import com.ritika.taskapi.dataclasses.SolvedResponse
import com.ritika.taskapi.dataclasses.SubmissionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    //fetch User Profile
    @GET("/{username}")
    suspend fun getUserProfile(
        @Path("username") username: String
    ): UserProfile

    //fetch Badge Api
    @GET("/{username}/badges")
    suspend fun getUserBadges(
        @Path("username") username: String
    ): UserBadges


    @GET("{username}/solved")
    suspend fun getSolvedProblems(
        @Path("username") username: String
    ): SolvedResponse

    @GET("{username}/submission?limit=20")
    suspend fun getSubmission(
        @Path("username") username: String
    ): SubmissionResponse
}