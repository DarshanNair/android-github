package com.darshan.github.toptrendinglist.injection

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.darshan.github.core.injection.scopes.PerActivity
import com.darshan.github.toptrendinglist.domain.loadtoptrendinglist.injection.LoadTopTrendingUseCaseModule
import com.darshan.github.toptrendinglist.domain.searchtoptrendinglist.injection.SearchTopTrendingUseCaseModule
import com.darshan.github.toptrendinglist.domain.sorttoptrendinglist.injection.SortTopTrendingUseCaseModule
import com.darshan.github.toptrendinglist.view.TopTrendingListActivity
import com.darshan.github.toptrendinglist.viewmodel.TopTrendingListViewModel
import com.darshan.github.toptrendinglist.viewmodel.TopTrendingListViewModelFactory
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        LoadTopTrendingUseCaseModule::class,
        SortTopTrendingUseCaseModule::class,
        SearchTopTrendingUseCaseModule::class
    ]
)
class TopTrendingListActivityModule {

    @Provides
    @PerActivity
    fun provideContext(listActivity: TopTrendingListActivity): Context = listActivity

    @Provides
    @PerActivity
    fun provideTopTrendingListViewModel(
        listActivity: TopTrendingListActivity,
        factoryList: TopTrendingListViewModelFactory
    ): TopTrendingListViewModel =
        ViewModelProviders.of(listActivity, factoryList).get(TopTrendingListViewModel::class.java)

    @Provides
    @PerActivity
    internal fun provideLayoutManager(context: Context): RecyclerView.LayoutManager =
        LinearLayoutManager(context)

}