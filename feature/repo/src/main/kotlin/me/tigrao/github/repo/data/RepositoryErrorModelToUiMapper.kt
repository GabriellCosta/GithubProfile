package me.tigrao.github.repo.data

import android.content.res.Resources
import me.tigrao.github.repo.R
import me.tigrao.github.repo.domain.model.RepositoryErrorModel

internal class RepositoryErrorModelToUiMapper(
    private val resources: Resources,
) {

    fun mapFrom(from: RepositoryErrorModel): String {
        val resource = when (from) {
            RepositoryErrorModel.GenericError -> R.string.repositories_error_generic
            RepositoryErrorModel.MaxOfRequestReach -> R.string.repositories_error_max_requests
        }

        return resources.getString(resource)
    }
}
