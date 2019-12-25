package com.darshan.github.core.network.api.injection

import android.content.Context
import com.darshan.github.core.injection.qualifiers.ForApplication
import com.darshan.github.core.injection.scopes.PerApplication
import com.darshan.github.core.network.api.AutoValueTypeAdapterFactory
import com.darshan.github.core.network.api.GithubApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class GithubApiModule {

    companion object {
        private const val CACHE_SIZE = 50 * 1024 * 1024
    }

    @Provides
    @PerApplication
    fun provideOkHttpClient(okHttpBuilder: OkHttpClient.Builder): OkHttpClient {
        return okHttpBuilder.build()
    }

    @Provides
    @PerApplication
    fun provideMangoApi(
        retrofitBuilder: Retrofit.Builder,
        client: OkHttpClient,
        gson: Gson
    ): GithubApi {
        return retrofitBuilder.client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://github-trending-api.now.sh/")
            .build()
            .create(GithubApi::class.java)
    }

    @Provides
    @PerApplication
    fun provideMangoApiGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapterFactory(AutoValueTypeAdapterFactory.create())
            .create()
    }

    @Provides
    @PerApplication
    fun provideOkHttpClientBuilder(cache: Cache): OkHttpClient.Builder {
        return OkHttpClient().newBuilder()
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .cache(cache)
            .connectTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
    }

    @Provides
    @PerApplication
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Provides
    @PerApplication
    fun provideCache(@ForApplication context: Context): Cache =
        Cache(context.cacheDir, CACHE_SIZE.toLong())

}
