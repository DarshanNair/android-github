package com.darshan.github.toptrendinglist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.darshan.github.core.network.model.TopTrending
import com.darshan.github.toptrendinglist.domain.loadtoptrendinglist.LoadTopTrendingUseCase
import com.darshan.github.toptrendinglist.domain.searchtoptrendinglist.SearchTopTrendingUseCase
import com.darshan.github.toptrendinglist.domain.sorttoptrendinglist.SortTopTrendingUseCase
import javax.inject.Inject

class TopTrendingListViewModelImpl @Inject internal constructor(
    private val loadTopTrendingUseCase: LoadTopTrendingUseCase,
    private val sortTopTrendingUseCase: SortTopTrendingUseCase,
    private var searchTopTrendingUseCase: SearchTopTrendingUseCase
) : TopTrendingListViewModel(), LoadTopTrendingUseCase.Callback,
    SearchTopTrendingUseCase.Callback, SortTopTrendingUseCase.Callback {

    private var topTrendingUsers: List<TopTrending.User> = emptyList()

    private val stateLiveData = MediatorLiveData<State>()

    init {
        loadTopTrendingUseCase.setCallback(this)
        sortTopTrendingUseCase.setCallback(this)
        searchTopTrendingUseCase.setCallback(this)
    }

    companion object {
        const val PREFERRED_LANGUAGE = "java"
        const val SINCE_PERIOD = "weekly"
    }

    override fun state(): LiveData<State> = stateLiveData

    override fun loadTopTrendingUsers() {
        stateLiveData.value = State.Loading
        loadTopTrendingUseCase.execute(PREFERRED_LANGUAGE, SINCE_PERIOD)
    }

    override fun onTopTrendFetchSuccess(topTrendingUsers: List<TopTrending.User>) {
        if (topTrendingUsers.isEmpty()) {
            stateLiveData.value = State.Empty
        } else {
            stateLiveData.value = State.Success(topTrendingUsers)
        }
        this.topTrendingUsers = topTrendingUsers
    }

    override fun onTopTrendFetchError(throwable: Throwable) {
        stateLiveData.value = State.Error
    }

    public override fun onCleared() {
        super.onCleared()
        loadTopTrendingUseCase.cleanup()
        sortTopTrendingUseCase.cleanup()
        sortTopTrendingUseCase.cleanup()
    }

    override fun sortTopTrendingUsers() {
        stateLiveData.value = State.Loading
        sortTopTrendingUseCase.execute(topTrendingUsers)
    }

    override fun searchTopTrendingList(name: String) {
        stateLiveData.value = State.Loading
        searchTopTrendingUseCase.execute(topTrendingUsers, name)
    }

    override fun onSearchTopTrendingDone(topTrendingUsers: List<TopTrending.User>) {
        if (topTrendingUsers.isEmpty()) {
            stateLiveData.value = State.Empty
        } else {
            stateLiveData.value = State.Success(topTrendingUsers)
        }
    }

    override fun onSortTopTrendingDone(topTrendingUsers: List<TopTrending.User>) {
        stateLiveData.value = State.Success(topTrendingUsers)
    }

}
