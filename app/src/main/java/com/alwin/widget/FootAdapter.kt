// package com.alwin.widget
//
// import android.view.LayoutInflater
// import android.view.View
// import android.view.ViewGroup
// import androidx.annotation.LayoutRes
// import androidx.paging.LoadState
// import androidx.paging.LoadStateAdapter
// import com.alwin.android.R
//
// class FooterAdapter(val adapter: GitHubAdapter) : LoadStateAdapter<NetworkStateItemViewHolder>() {
//     override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
//         holder.bindData(loadState, 0)
//     }
//
//     override fun onCreateViewHolder(
//         parent: ViewGroup,
//         loadState: LoadState
//     ): NetworkStateItemViewHolder {
//         val view = inflateView(parent, R.layout.recycie_item_network_state)
//         return NetworkStateItemViewHolder(view) { adapter.retry() }
//     }
//
//     private fun inflateView(viewGroup: ViewGroup, @LayoutRes viewType: Int): View {
//         val layoutInflater = LayoutInflater.from(viewGroup.context)
//         return layoutInflater.inflate(viewType, viewGroup, false)
//     }
// }