package me.tigrao.github.repo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import me.tigrao.github.repo.databinding.ItemNetworkStateBinding

internal class RepoLoadStateAdapter(
    private val adapter: RepoAdapter
) :
    LoadStateAdapter<RepoLoadStateAdapter.NetworkStateItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder {
        return NetworkStateItemViewHolder(parent) {
            adapter.retry()
        }
    }

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class NetworkStateItemViewHolder(
        private val binding: ItemNetworkStateBinding,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            operator fun invoke(
                parent: ViewGroup,
                retryCallback: () -> Unit
            ): NetworkStateItemViewHolder {
                val binding =
                    ItemNetworkStateBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )

                return NetworkStateItemViewHolder(binding, retryCallback)
            }
        }

        init {
            binding.retry.setOnClickListener { retryCallback() }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                progress.isVisible = loadState is LoadState.Loading
                retry.isVisible = loadState is LoadState.Error
                message.isVisible =
                    !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
                message.text = (loadState as? LoadState.Error)?.error?.message
            }
        }
    }
}
