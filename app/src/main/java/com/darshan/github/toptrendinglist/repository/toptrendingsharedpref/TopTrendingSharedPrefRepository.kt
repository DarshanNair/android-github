package com.darshan.github.toptrendinglist.repository.toptrendingsharedpref

import com.darshan.github.core.network.model.TopTrending

interface TopTrendingSharedPrefRepository {

    var topGithubTrendingResponse: List<TopTrending.User>

}
