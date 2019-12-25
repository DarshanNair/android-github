package com.darshan.github.toptrendinglist.domain.sorttoptrendinglist

import com.darshan.github.core.domain.BaseUseCase
import com.darshan.github.core.injection.qualifiers.ForIoThread
import com.darshan.github.core.injection.qualifiers.ForMainThread
import com.darshan.github.core.network.model.TopTrending
import com.darshan.github.toptrendinglist.domain.sorttoptrendinglist.SortTopTrendingUseCase.Callback
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SortTopTrendingUseCaseImpl @Inject constructor(
    compositeDisposable: CompositeDisposable,
    @ForIoThread private val ioScheduler: Scheduler,
    @ForMainThread private val mainScheduler: Scheduler
) : BaseUseCase(compositeDisposable), SortTopTrendingUseCase {

    private var callback: Callback? = null

    override fun execute(topTrendingUsers: List<TopTrending.User>) {
        trackDisposable(
            Single.fromCallable { topTrendingUsers.sortedWith(compareBy { it.name.toLowerCase() }) }
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(::onSuccess)
        )
    }

    override fun setCallback(callback: Callback) {
        this.callback = callback
    }

    private fun onSuccess(topTrendingUsers: List<TopTrending.User>) {
        callback?.onSortTopTrendingDone(topTrendingUsers)
    }

    override fun cleanup() {
        callback = null
        super.cleanup()
    }

}
