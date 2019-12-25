package com.darshan.github.toptrendingdetail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.darshan.androidgithub.R
import com.darshan.github.core.imageloader.ImageLoader
import com.darshan.github.core.network.model.TopTrending
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_top_trending_detail.*
import javax.inject.Inject

class TopTrendingDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader

    companion object {

        private const val KEY_TOP_TRENDING_USER = "KEY_TOP_TRENDING_USER"

        fun getStartIntent(context: Context, topTrendingUser: TopTrending.User) =
            Intent(context, TopTrendingDetailActivity::class.java).putExtra(
                KEY_TOP_TRENDING_USER,
                topTrendingUser
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        setContentView(R.layout.activity_top_trending_detail)

        setData()
    }

    private fun setData() {
        val topTrendingUser = intent.getParcelableExtra(KEY_TOP_TRENDING_USER) as? TopTrending.User

        topTrendingUser?.let {
            user_name.text = it.username
            name.text = it.name
            user_url.text = it.url
            imageLoader.draw(user_avatar, it.avatar)
            repo_name.text = it.repo.name
            repo_description.text = it.repo.description
            repo_url.text = it.repo.url
        }

    }

}
