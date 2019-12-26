package com.darshan.github.toptrendinglist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.darshan.github.core.network.model.TopTrending
import com.darshan.github.toptrendinglist.domain.loadtoptrendinglist.LoadTopTrendingUseCase
import com.darshan.github.toptrendinglist.domain.searchtoptrendinglist.SearchTopTrendingUseCase
import com.darshan.github.toptrendinglist.domain.sorttoptrendinglist.SortTopTrendingUseCase

abstract class TopTrendingListViewModel : ViewModel(),
    LoadTopTrendingUseCase.Callback,
    SearchTopTrendingUseCase.Callback,
    SortTopTrendingUseCase.Callback {

    sealed class State {
        object Loading : State()
        data class Success(val topTrendingUsers: List<TopTrending.User>) : State()
        object Empty : State()
        object Error : State()
    }

    abstract fun state(): LiveData<State>

    abstract fun loadTopTrendingUsers()

    abstract fun sortTopTrendingUsers()

    abstract fun searchTopTrendingList(name: String)

}
