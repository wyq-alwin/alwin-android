package com.alwin.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alwin.android.R
import com.alwin.api.HomeApi
import com.alwin.model.AlwinResponse
import com.alwin.model.HomeArticleResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

// import com.alwin.main.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */

@AndroidEntryPoint
class HomeFlowFragment : Fragment() {
    @Inject
    lateinit var homeApi: HomeApi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_flow_list, container, false)
        // if (view is RecyclerView) {
        //     with(view) {
        //         layoutManager = when {
        //             columnCount <= 1 -> LinearLayoutManager(context)
        //             else -> GridLayoutManager(context, columnCount)
        //         }
        //         adapter = HomeFlowRecyclerViewAdapter(PlaceholderContent.ITEMS)
        //     }
        // }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            homeApi.getHomeArticles(1).enqueue(object :
                Callback<AlwinResponse<HomeArticleResponse>> {
                override fun onResponse(
                    call: Call<AlwinResponse<HomeArticleResponse>>,
                    response: Response<AlwinResponse<HomeArticleResponse>>
                ) {
                    response.body()?.data?.datas
                    println()
                }

                override fun onFailure(
                    call: Call<AlwinResponse<HomeArticleResponse>>,
                    t: Throwable
                ) {
                }
            })
        }
    }
}