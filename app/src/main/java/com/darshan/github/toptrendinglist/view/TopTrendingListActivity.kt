package com.darshan.github.toptrendinglist.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.darshan.androidgithub.R
import com.darshan.github.toptrendingdetail.view.TopTrendingDetailActivity
import com.darshan.github.toptrendinglist.view.adapter.TopTrendingListAdapter
import com.darshan.github.toptrendinglist.viewmodel.TopTrendingListViewModel
import com.darshan.github.toptrendinglist.viewmodel.TopTrendingListViewModel.State
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_top_trending_list.*
import kotlinx.android.synthetic.main.view_top_trending_list_loaded.*
import javax.inject.Inject

class TopTrendingListActivity : AppCompatActivity() {

    @Inject
    lateinit var topTrendingListViewModel: TopTrendingListViewModel
    @Inject
    lateinit var topTrendingListAdapter: TopTrendingListAdapter
    @Inject
    lateinit var topTrendingLayoutManager: RecyclerView.LayoutManager

    enum class UIState {
        LOADING,
        LOADED,
        EMPTY,
        ERROR
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        setContentView(R.layout.activity_top_trending_list)

        setupSearch()

        setupSort()

        setupRecyclerView()

        topTrendingListViewModel.apply {
            state().observe(
                this@TopTrendingListActivity,
                Observer { it?.let { onTopTrendingUsersLoaded(it) } })
            loadTopTrendingUsers()
        }
    }

    private fun setupRecyclerView() {
        top_trending_list.layoutManager = topTrendingLayoutManager
        top_trending_list.adapter = topTrendingListAdapter
        topTrendingListAdapter.setOnClickListener {
            startActivity(TopTrendingDetailActivity.getStartIntent(this@TopTrendingListActivity, it))
        }
    }

    private fun setupSearch() {
        search_button.setOnClickListener {
            val text = search_input_field.text.toString()
            if(text.isNotBlank()) {
                topTrendingListViewModel.searchTopTrendingList(text)
            }
        }
    }

    private fun setupSort() {
        sort_alpha.setOnClickListener { topTrendingListViewModel.sortTopTrendingUsers() }
    }

    private fun onTopTrendingUsersLoaded(state: State) {
        when (state) {
            State.Loading -> view_flipper_top_trending.displayedChild = UIState.LOADING.ordinal
            is State.Success -> {
                view_flipper_top_trending.displayedChild = UIState.LOADED.ordinal
                topTrendingListAdapter.setTopTrendingUsers(state.topTrendingUsers)
            }
            State.Empty -> view_flipper_top_trending.displayedChild = UIState.EMPTY.ordinal
            State.Error -> view_flipper_top_trending.displayedChild = UIState.ERROR.ordinal
        }
    }

}
