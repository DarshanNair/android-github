package com.darshan.github.core.injection

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.bumptech.glide.Glide
import com.darshan.androidgithub.BuildConfig
import com.darshan.github.core.imageloader.ImageLoader
import com.darshan.github.core.injection.qualifiers.ForApplication
import com.darshan.github.core.injection.qualifiers.ForIoThread
import com.darshan.github.core.injection.qualifiers.ForMainThread
import com.darshan.github.core.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class BaseModule {

    @Provides
    @ForApplication
    fun provideContext(application: Application): Context = application

    @Provides
    @ForApplication
    fun provideApplication(application: Application): Application = application

    @Provides
    @PerApplication
    @ForIoThread
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @PerApplication
    @ForMainThread
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @PerApplication
    fun provideImageLoader(@ForApplication context: Context) = ImageLoader(Glide.with(context))

    @Provides
    @PerApplication
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

}
