package me.tigrao.github.repo.view.adapter

import androidx.recyclerview.widget.DiffUtil
import me.tigrao.github.repo.domain.model.RepositoryDataModel
import me.tigrao.github.repo.domain.model.RepositoryModel
import me.tigrao.github.repo.presentation.model.ListItemUiModel

internal class RepoDiffConfig : DiffUtil.ItemCallback<RepositoryDataModel>() {
    override fun areItemsTheSame(oldItem: RepositoryDataModel, newItem: RepositoryDataModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: RepositoryDataModel, newItem: RepositoryDataModel): Boolean {
        return oldItem == newItem
    }
}
