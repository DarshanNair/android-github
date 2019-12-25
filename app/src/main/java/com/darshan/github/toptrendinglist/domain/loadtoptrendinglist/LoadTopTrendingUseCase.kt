package com.darshan.github.toptrendinglist.domain.loadtoptrendinglist

import com.darshan.github.core.domain.UseCase
import com.darshan.github.core.network.model.TopTrending


interface LoadTopTrendingUseCase : UseCase {

    fun execute(language: String, since: String)

    fun setCallback(callback: Callback)

    interface Callback {
        fun onTopTrendFetchSuccess(topTrendingUsers: List<TopTrending.User>)
        fun onTopTrendFetchError(throwable: Throwable)
    }

}