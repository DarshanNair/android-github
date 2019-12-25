package com.darshan.github.toptrendinglist.domain.sorttoptrendinglist

import com.darshan.github.core.domain.UseCase
import com.darshan.github.core.network.model.TopTrending

interface SortTopTrendingUseCase : UseCase {

    fun execute(topTrendingUsers: List<TopTrending.User>)

    fun setCallback(callback: Callback)

    interface Callback {
        fun onSortTopTrendingDone(topTrendingUsers: List<TopTrending.User>)
    }

}