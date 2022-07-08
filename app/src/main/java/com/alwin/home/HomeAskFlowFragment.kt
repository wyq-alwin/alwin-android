package com.alwin.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alwin.android.databinding.FragmentHomeAskFlowBinding
import com.alwin.android.databinding.ItemHomeArticleBinding
import com.alwin.home.viewholder.ArticleViewHolder
import com.alwin.home.viewmodel.HomeFlowViewModel
import com.alwin.model.Article
import com.alwin.util.dp2px
import com.alwin.widget.SpacesItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 只请求一次数据
 */
class HomeAskFlowFragment : Fragment() {

    private var binding: FragmentHomeAskFlowBinding? = null

    private val homeFlowViewModel: HomeFlowViewModel by activityViewModels()

    private fun binding() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeAskFlowBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeFlowAdapter = ArticleAdapter()
        binding().recyclerview.apply {
            adapter = homeFlowAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SpacesItemDecoration(4.dp2px))
        }
        lifecycleScope.launch {
            homeFlowViewModel.getAskArticleData()
        }
        homeFlowViewModel.askFlowModel.observe(viewLifecycleOwner) {
            homeFlowAdapter.data = it
            homeFlowAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    inner class ArticleAdapter : RecyclerView.Adapter<ArticleViewHolder>() {
        var data: List<Article> = emptyList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
            val binding =
                ItemHomeArticleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return ArticleViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
            holder.bind(data[position])
        }

        override fun getItemCount(): Int {
            return data.size
        }
    }
}