package com.darshan.github.toptrendinglist.injection

import com.darshan.github.core.injection.scopes.PerActivity
import com.darshan.github.toptrendinglist.view.TopTrendingListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TopTrendingListActivityBuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [TopTrendingListActivityModule::class])
    abstract fun bindTopTrendingListActivity(): TopTrendingListActivity

}