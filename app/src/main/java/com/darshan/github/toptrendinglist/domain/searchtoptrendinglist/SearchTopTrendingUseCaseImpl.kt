package com.darshan.github.toptrendinglist.domain.searchtoptrendinglist

import com.darshan.github.core.domain.BaseUseCase
import com.darshan.github.core.injection.qualifiers.ForIoThread
import com.darshan.github.core.injection.qualifiers.ForMainThread
import com.darshan.github.core.network.model.TopTrending
import com.darshan.github.toptrendinglist.domain.searchtoptrendinglist.SearchTopTrendingUseCase.Callback
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SearchTopTrendingUseCaseImpl @Inject constructor(
    compositeDisposable: CompositeDisposable,
    @ForIoThread private val ioScheduler: Scheduler,
    @ForMainThread private val mainScheduler: Scheduler
) : BaseUseCase(compositeDisposable), SearchTopTrendingUseCase {

    private var callback: Callback? = null

    override fun execute(topTrendingUsers: List<TopTrending.User>, name: String) {
        trackDisposable(
            Single.fromCallable { topTrendingUsers.filter {
                it.name.toLowerCase().contains(name.toLowerCase())
                        || it.username.toLowerCase().contains(name.toLowerCase())
            } }
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(::onSuccess)
        )
    }

    override fun setCallback(callback: Callback) {
        this.callback = callback
    }

    private fun onSuccess(topTrendingUsers: List<TopTrending.User>) {
        callback?.onSearchTopTrendingDone(topTrendingUsers)
    }

    override fun cleanup() {
        callback = null
        super.cleanup()
    }

}
