package com.darshan.github.toptrendinglist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.darshan.github.toptrendinglist.domain.loadtoptrendinglist.LoadTopTrendingUseCase
import com.darshan.github.toptrendinglist.domain.searchtoptrendinglist.SearchTopTrendingUseCase
import com.darshan.github.toptrendinglist.domain.sorttoptrendinglist.SortTopTrendingUseCase
import javax.inject.Inject

class TopTrendingListViewModelFactory @Inject constructor(
    private val loadTopTrendingUseCase: LoadTopTrendingUseCase,
    private val sortTopTrendingUseCase: SortTopTrendingUseCase,
    private val searchTopTrendingUseCase: SearchTopTrendingUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        TopTrendingListViewModelImpl(
            loadTopTrendingUseCase,
            sortTopTrendingUseCase,
            searchTopTrendingUseCase
        ) as T

}