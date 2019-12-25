package com.darshan.github.toptrendingdetail.injection

import com.darshan.github.core.injection.scopes.PerActivity
import com.darshan.github.toptrendingdetail.view.TopTrendingDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TopTrendingDetailActivityBuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [TopTrendingDetailActivityModule::class])
    abstract fun bindTopTrendingDetailActivity(): TopTrendingDetailActivity

}