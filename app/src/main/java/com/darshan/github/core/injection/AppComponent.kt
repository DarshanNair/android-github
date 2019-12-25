package com.darshan.github.core.injection

import android.app.Application
import com.darshan.github.core.GithubApplication
import com.darshan.github.core.injection.scopes.PerApplication
import com.darshan.github.core.network.api.injection.GithubApiModule
import com.darshan.github.toptrendingdetail.injection.TopTrendingDetailActivityBuilderModule
import com.darshan.github.toptrendinglist.injection.TopTrendingListActivityBuilderModule
import dagger.BindsInstance
import dagger.Component

@PerApplication
@Component(
    modules = [
        BaseModule::class,
        GithubApiModule::class,
        TopTrendingListActivityBuilderModule::class,
        TopTrendingDetailActivityBuilderModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: GithubApplication)

}