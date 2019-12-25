package com.darshan.github.toptrendinglist.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.darshan.github.core.imageloader.ImageLoader
import com.darshan.github.core.network.model.TopTrending
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_top_trending_list.*

class TopTrendingListViewHolder(
    override val containerView: View,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(topTrendingUser: TopTrending.User) {
        name.text = topTrendingUser.name
        user_name.text = topTrendingUser.username
        imageLoader.draw(user_avatar, topTrendingUser.avatar)
    }

}