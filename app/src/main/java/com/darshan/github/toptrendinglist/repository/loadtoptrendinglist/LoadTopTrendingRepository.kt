package com.darshan.github.toptrendinglist.repository.loadtoptrendinglist

import com.darshan.github.core.network.model.TopTrending
import io.reactivex.Single

interface LoadTopTrendingRepository {

    fun getTopTrending(language: String, since: String): Single<List<TopTrending.User>>

}