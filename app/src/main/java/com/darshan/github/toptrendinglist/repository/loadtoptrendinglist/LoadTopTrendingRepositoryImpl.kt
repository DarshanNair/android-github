package com.darshan.github.toptrendinglist.repository.loadtoptrendinglist

import com.darshan.github.core.network.api.GithubApi
import com.darshan.github.core.network.model.TopTrending
import io.reactivex.Single
import javax.inject.Inject

class LoadTopTrendingRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi
) : LoadTopTrendingRepository {

    override fun getTopTrending(language: String, since: String): Single<List<TopTrending.User>> =
        githubApi.getTopTrending(language, since)

}