package com.darshan.github.toptrendinglist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.darshan.github.core.network.model.TopTrending
import com.darshan.github.toptrendinglist.domain.loadtoptrendinglist.LoadTopTrendingUseCase
import com.darshan.github.toptrendinglist.domain.searchtoptrendinglist.SearchTopTrendingUseCase
import com.darshan.github.toptrendinglist.domain.sorttoptrendinglist.SortTopTrendingUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.Mockito.reset
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class TopTrendingListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: TopTrendingListViewModelImpl

    @Mock
    private lateinit var mockLoadTopTrendingUseCase: LoadTopTrendingUseCase
    @Mock
    private lateinit var mockSortTopTrendingUseCase: SortTopTrendingUseCase
    @Mock
    private lateinit var mockSearchTopTrendingUseCase: SearchTopTrendingUseCase
    @Mock
    private lateinit var mockObserver: Observer<TopTrendingListViewModel.State>
    @Mock
    private lateinit var mockThrowable: Throwable
    @Mock
    private lateinit var mockTopTrendingUsers: List<TopTrending.User>


    @Before
    fun setUp() {
        subject = TopTrendingListViewModelImpl(
            mockLoadTopTrendingUseCase,
            mockSortTopTrendingUseCase,
            mockSearchTopTrendingUseCase
        )
        subject.state().observeForever(mockObserver)
    }

    @Test
    fun `Load Top Trending Users`() {
        // GIVEN
        reset(mockLoadTopTrendingUseCase)

        // WHEN
        subject.loadTopTrendingUsers()

        // THEN
        thenObserverShouldReceiveCorrectStates(TopTrendingListViewModel.State.Loading)
        then(mockLoadTopTrendingUseCase).should().execute(anyString(), anyString())
        then(mockLoadTopTrendingUseCase).shouldHaveNoMoreInteractions()
    }

    @Test
    fun `On Load Top Trending Users - Success with Users non empty`() {
        // GIVEN
        given(mockTopTrendingUsers.isEmpty()).willReturn(false)

        // WHEN
        subject.onTopTrendFetchSuccess(mockTopTrendingUsers)

        // THEN
        thenObserverShouldReceiveCorrectStates(TopTrendingListViewModel.State.Success(mockTopTrendingUsers))
    }

    @Test
    fun `On Load Top Trending Users - Success with Users empty`() {
        // GIVEN
        given(mockTopTrendingUsers.isEmpty()).willReturn(true)

        // WHEN
        subject.onTopTrendFetchSuccess(mockTopTrendingUsers)

        // THEN
        thenObserverShouldReceiveCorrectStates(TopTrendingListViewModel.State.Empty)
    }

    @Test
    fun `On Load Top Trending Users - Network Error`() {
        // WHEN
        subject.onTopTrendFetchError(mockThrowable)

        // THEN
        thenObserverShouldReceiveCorrectStates(TopTrendingListViewModel.State.Error)
    }

    @Test
    fun `Clean Up on destroy`() {
        // GIVEN
        reset(mockLoadTopTrendingUseCase)
        reset(mockSortTopTrendingUseCase)
        reset(mockSearchTopTrendingUseCase)

        // WHEN
        subject.onCleared()

        // THEN
        then(mockLoadTopTrendingUseCase).should().cleanup()
        then(mockLoadTopTrendingUseCase).shouldHaveNoMoreInteractions()
        then(mockSortTopTrendingUseCase).should().cleanup()
        then(mockSortTopTrendingUseCase).shouldHaveNoMoreInteractions()
        then(mockSearchTopTrendingUseCase).should().cleanup()
        then(mockSearchTopTrendingUseCase).shouldHaveNoMoreInteractions()
    }

    private fun thenObserverShouldReceiveCorrectStates(vararg expected: TopTrendingListViewModel.State) {
        expected.forEach { then(mockObserver).should().onChanged(it) }
        then(mockObserver).shouldHaveNoMoreInteractions()
    }

}
