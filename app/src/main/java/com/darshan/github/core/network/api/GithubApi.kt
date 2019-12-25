package com.darshan.github.core.network.api

import com.darshan.github.core.network.model.TopTrending
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("developers")
    fun getTopTrending(
        @Query("language") language: String,
        @Query("since") since: String
    ): Single<List<TopTrending.User>>

}
