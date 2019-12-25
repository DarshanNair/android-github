package com.darshan.github.toptrendinglist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.darshan.github.core.network.model.TopTrending

abstract class TopTrendingListViewModel : ViewModel() {

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
