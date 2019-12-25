package com.darshan.github.toptrendinglist.domain.loadtoptrendinglist.injection

import com.darshan.github.toptrendinglist.domain.loadtoptrendinglist.LoadTopTrendingUseCase
import com.darshan.github.toptrendinglist.domain.loadtoptrendinglist.LoadTopTrendingUseCaseImpl
import com.darshan.github.toptrendinglist.repository.loadtoptrendinglist.injection.LoadTopTrendingRepositoryModule
import com.darshan.github.toptrendinglist.repository.toptrendingsharedpref.injection.TopTrendingSharedPrefRepositoryModule
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module(
    includes = [
        LoadTopTrendingRepositoryModule::class,
        TopTrendingSharedPrefRepositoryModule::class
    ]
)
class LoadTopTrendingUseCaseModule {

    @Provides
    fun provideLoadTopTrendingUseCase(usecase: LoadTopTrendingUseCaseImpl): LoadTopTrendingUseCase =
        usecase

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

}