package com.darshan.github.toptrendinglist.domain.searchtoptrendinglist.injection

import com.darshan.github.toptrendinglist.domain.searchtoptrendinglist.SearchTopTrendingUseCase
import com.darshan.github.toptrendinglist.domain.searchtoptrendinglist.SearchTopTrendingUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class SearchTopTrendingUseCaseModule {

    @Provides
    fun provideSearchTopTrendingUseCase(usecase: SearchTopTrendingUseCaseImpl): SearchTopTrendingUseCase =
        usecase

}