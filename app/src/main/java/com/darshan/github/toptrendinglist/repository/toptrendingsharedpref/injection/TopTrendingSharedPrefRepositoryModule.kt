package com.darshan.github.toptrendinglist.repository.toptrendingsharedpref.injection

import com.darshan.github.toptrendinglist.repository.toptrendingsharedpref.TopTrendingSharedPrefRepository
import com.darshan.github.toptrendinglist.repository.toptrendingsharedpref.TopTrendingSharedPrefRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class TopTrendingSharedPrefRepositoryModule {

    @Provides
    fun provideTopTrendingSharedPrefRepository(repository: TopTrendingSharedPrefRepositoryImpl): TopTrendingSharedPrefRepository =
        repository

}