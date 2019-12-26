package com.darshan.github.toptrendinglist.repository.loadtoptrendinglist

import com.darshan.github.core.network.api.GithubApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class LoadTopTrendingRepositoryTest {

    private lateinit var subject: LoadTopTrendingRepository

    @Mock
    private lateinit var mockGithubApi: GithubApi

    companion object {
        const val PREFERRED_LANGUAGE = "java"
        const val SINCE_PERIOD = "weekly"
    }


    @Before
    fun setUp() {
        subject = LoadTopTrendingRepositoryImpl(mockGithubApi)
    }

    @Test
    fun `Load top trending Github`() {
        // WHEN
        subject.getTopTrending(PREFERRED_LANGUAGE, SINCE_PERIOD)

        // THEN
        then(mockGithubApi).should().getTopTrending(PREFERRED_LANGUAGE, SINCE_PERIOD)
    }

}
