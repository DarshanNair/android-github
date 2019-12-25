package com.darshan.github.toptrendinglist.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.darshan.androidgithub.R
import com.darshan.github.core.imageloader.ImageLoader
import com.darshan.github.core.network.model.TopTrending
import javax.inject.Inject

class TopTrendingListAdapter @Inject constructor(
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var clickCallback: (topTrendingUser: TopTrending.User) -> Unit
    private var topTrendingUsers = emptyList<TopTrending.User>()

    fun setTopTrendingUsers(topTrendingUsers: List<TopTrending.User>) {
        this.topTrendingUsers = topTrendingUsers
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: (topTrendingUser: TopTrending.User) -> Unit) {
        clickCallback = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return TopTrendingListViewHolder(view, imageLoader).apply {
            itemView.setOnClickListener {
                topTrendingUsers.getOrNull(adapterPosition)?.let { clickCallback(it) }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val recentSearchesViewHolder = holder as? TopTrendingListViewHolder
        recentSearchesViewHolder?.bind(topTrendingUsers[position])
    }

    override fun getItemCount() = topTrendingUsers.size

    override fun getItemViewType(position: Int) = R.layout.item_top_trending_list

}