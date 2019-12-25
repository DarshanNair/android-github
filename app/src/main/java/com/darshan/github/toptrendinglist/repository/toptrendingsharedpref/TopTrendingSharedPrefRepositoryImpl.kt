package com.darshan.github.toptrendinglist.repository.toptrendingsharedpref

import android.content.SharedPreferences
import com.darshan.github.core.network.model.TopTrending
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class TopTrendingSharedPrefRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : TopTrendingSharedPrefRepository {

    companion object {
        private const val TOP_GITHUB_TRENDING_RESPONSE = "top_github_trending_response"
    }

    override var topGithubTrendingResponse: List<TopTrending.User>
        get() {
            return sharedPreferences.getString(TOP_GITHUB_TRENDING_RESPONSE, null)?.let {
                gson.fromJson<List<TopTrending.User>>(it, object : TypeToken<List<TopTrending.User>>() {}.type)
            } ?: emptyList()
        }
        set(topGithubTrendingResponse) {
            sharedPreferences.edit().putString(TOP_GITHUB_TRENDING_RESPONSE, gson.toJson(topGithubTrendingResponse)).apply()
        }

}
