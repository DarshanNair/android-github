package com.darshan.github.toptrendinglist.domain.loadtoptrendinglist

import com.darshan.github.core.domain.BaseUseCase
import com.darshan.github.core.extensions.isNetworkError
import com.darshan.github.core.injection.qualifiers.ForIoThread
import com.darshan.github.core.injection.qualifiers.ForMainThread
import com.darshan.github.core.network.model.TopTrending
import com.darshan.github.toptrendinglist.domain.loadtoptrendinglist.LoadTopTrendingUseCase.Callback
import com.darshan.github.toptrendinglist.repository.toptrendingsharedpref.TopTrendingSharedPrefRepository
import com.darshan.github.toptrendinglist.repository.loadtoptrendinglist.LoadTopTrendingRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoadTopTrendingUseCaseImpl @Inject constructor(
    compositeDisposable: CompositeDisposable,
    private val loadTopTrendingRepository: LoadTopTrendingRepository,
    private val topTrendingSharedPrefRepository: TopTrendingSharedPrefRepository,
    @ForIoThread private val ioScheduler: Scheduler,
    @ForMainThread private val mainScheduler: Scheduler
) : BaseUseCase(compositeDisposable), LoadTopTrendingUseCase {

    private var callback: Callback? = null

    override fun execute(language: String, since: String) {
        trackDisposable(
            loadTopTrendingRepository.getTopTrending(language, since)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(::onSuccess, ::onError)
        )
    }

    override fun setCallback(callback: Callback) {
        this.callback = callback
    }

    private fun onSuccess(topTrendingUsers: List<TopTrending.User>) {
        cacheTopTrendingResponse(topTrendingUsers)
        callback?.onTopTrendFetchSuccess(topTrendingUsers)
    }

    private fun onError(throwable: Throwable) {
        if(throwable.isNetworkError()) {
            callback?.onTopTrendFetchSuccess(topTrendingSharedPrefRepository.topGithubTrendingResponse)
        } else {
            callback?.onTopTrendFetchError(throwable)
        }
    }

    private fun cacheTopTrendingResponse(topTrendingUsers: List<TopTrending.User>) {
        topTrendingSharedPrefRepository.topGithubTrendingResponse = topTrendingUsers
    }

    override fun cleanup() {
        callback = null
        super.cleanup()
    }

}
