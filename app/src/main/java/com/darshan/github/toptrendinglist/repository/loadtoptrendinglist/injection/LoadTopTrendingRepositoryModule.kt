package com.darshan.github.toptrendinglist.repository.loadtoptrendinglist.injection

import com.darshan.github.toptrendinglist.repository.loadtoptrendinglist.LoadTopTrendingRepository
import com.darshan.github.toptrendinglist.repository.loadtoptrendinglist.LoadTopTrendingRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class LoadTopTrendingRepositoryModule {

    @Provides
    fun provideLoadTopTrendingRepository(repository: LoadTopTrendingRepositoryImpl): LoadTopTrendingRepository =
        repository

}