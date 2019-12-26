package com.darshan.github.toptrendinglist.domain.loadtoptrendinglist

import com.darshan.github.core.network.model.TopTrending
import com.darshan.github.toptrendinglist.repository.loadtoptrendinglist.LoadTopTrendingRepository
import com.darshan.github.toptrendinglist.repository.toptrendingsharedpref.TopTrendingSharedPrefRepository
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import java.io.IOException

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class LoadTopTrendingUseCaseTest {

    private lateinit var subject: LoadTopTrendingUseCase

    @Mock
    private lateinit var mockCallback: LoadTopTrendingUseCase.Callback
    @Mock
    private lateinit var mockCompositeDisposable: CompositeDisposable
    @Mock
    private lateinit var mockLoadTopTrendingRepository: LoadTopTrendingRepository
    @Mock
    private lateinit var mockTopTrendingSharedPrefRepository: TopTrendingSharedPrefRepository
    @Mock
    private lateinit var mockTopTrendingUser: TopTrending.User
    @Mock
    private lateinit var mockNetworkError: IOException
    @Mock
    private lateinit var mockHttpException: HttpException

    private lateinit var topTrendingUsers: List<TopTrending.User>

    companion object {
        const val PREFERRED_LANGUAGE = "java"
        const val SINCE_PERIOD = "weekly"
    }


    @Before
    fun setUp() {
        subject = LoadTopTrendingUseCaseImpl(
            mockCompositeDisposable,
            mockLoadTopTrendingRepository,
            mockTopTrendingSharedPrefRepository,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )

        topTrendingUsers = listOf(mockTopTrendingUser)
        subject.setCallback(mockCallback)
    }

    @Test
    fun `Success - Fetch Top Trending Github Users`() {
        // GIVEN
        given(mockLoadTopTrendingRepository.getTopTrending(anyString(), anyString())).willReturn(Single.just(topTrendingUsers))

        // WHEN
        subject.execute(PREFERRED_LANGUAGE, SINCE_PERIOD)

        // THEN
        then(mockTopTrendingSharedPrefRepository).should().topGithubTrendingResponse = topTrendingUsers
        then(mockTopTrendingSharedPrefRepository).shouldHaveNoMoreInteractions()
        then(mockCallback).should().onTopTrendFetchSuccess(topTrendingUsers)
        then(mockCallback).shouldHaveNoMoreInteractions()
    }

    @Test
    fun `Network Error - Fetch Top Trending Github Users`() {
        // GIVEN
        given(mockLoadTopTrendingRepository.getTopTrending(anyString(), anyString())).willReturn(Single.error(mockNetworkError))
        given(mockTopTrendingSharedPrefRepository.topGithubTrendingResponse).willReturn(topTrendingUsers)

        // WHEN
        subject.execute(PREFERRED_LANGUAGE, SINCE_PERIOD)

        // THEN
        then(mockTopTrendingSharedPrefRepository).should().topGithubTrendingResponse
        then(mockTopTrendingSharedPrefRepository).shouldHaveNoMoreInteractions()
        then(mockCallback).should().onTopTrendFetchSuccess(topTrendingUsers)
        then(mockCallback).shouldHaveNoMoreInteractions()
    }

    @Test
    fun `HTTP Error - Fetch Top Trending Github Users`() {
        // GIVEN
        given(mockLoadTopTrendingRepository.getTopTrending(anyString(), anyString())).willReturn(Single.error(mockHttpException))

        // WHEN
        subject.execute(PREFERRED_LANGUAGE, SINCE_PERIOD)

        // THEN
        then(mockCallback).should().onTopTrendFetchError(mockHttpException)
        then(mockCallback).shouldHaveNoMoreInteractions()
    }

    @Test
    fun `cleanup on destroy`() {
        // WHEN
        subject.cleanup()

        // THEN
        then(mockCompositeDisposable).should().clear()
    }

}
