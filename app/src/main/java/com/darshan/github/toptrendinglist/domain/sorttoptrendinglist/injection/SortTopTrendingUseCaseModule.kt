package com.darshan.github.toptrendinglist.domain.sorttoptrendinglist.injection

import com.darshan.github.toptrendinglist.domain.sorttoptrendinglist.SortTopTrendingUseCase
import com.darshan.github.toptrendinglist.domain.sorttoptrendinglist.SortTopTrendingUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class SortTopTrendingUseCaseModule {

    @Provides
    fun provideSortTopTrendingUseCase(usecase: SortTopTrendingUseCaseImpl): SortTopTrendingUseCase =
        usecase

}