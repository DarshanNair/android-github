package com.darshan.github.toptrendinglist.domain.searchtoptrendinglist

import com.darshan.github.core.domain.UseCase
import com.darshan.github.core.network.model.TopTrending

interface SearchTopTrendingUseCase : UseCase {

    fun execute(topTrendingUsers: List<TopTrending.User>, name: String)

    fun setCallback(callback: Callback)

    interface Callback {
        fun onSearchTopTrendingDone(topTrendingUsers: List<TopTrending.User>)
    }

}