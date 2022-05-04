package me.tigrao.github.repo.domain

import me.tigrao.github.repo.data.api.model.RepositoriesResponse
import me.tigrao.github.repo.domain.model.RepositoryDataModel
import me.tigrao.github.repo.domain.model.RepositoryModel

internal class FetchRepositorySuccessMapper {

    fun mapFrom(from: RepositoriesResponse): RepositoryModel {
        val data = from.items.map { map ->
            RepositoryDataModel(
                map.owner.avatar,
                map.name,
                map.owner.login,
                map.stars,
                map.forks,
                map.description.orEmpty()
            )
        }

        return RepositoryModel(
            data = data,
        )
    }
}
