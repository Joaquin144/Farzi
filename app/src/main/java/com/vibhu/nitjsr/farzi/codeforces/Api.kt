package com.vibhu.nitjsr.farzi.codeforces

import retrofit2.Response
import retrofit2.http.GET

interface Api {
    //Our objective is to get contests list from codeforces.
    @GET("/contest.list?gym=false")
    suspend fun getContests():Response<List<ItemContest>>

}