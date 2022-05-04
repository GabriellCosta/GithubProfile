package me.tigrao.github.repo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.tigrao.github.repo.domain.FetchRepositoryParameters
import me.tigrao.github.repo.domain.FetchRepositoryUseCase
import me.tigrao.github.repo.domain.model.RepositoryDataModel

internal class RepoDataSource(
    private val fetchRepositoryUseCase: FetchRepositoryUseCase,
    private val repositoryErrorModelToUiMapper: RepositoryErrorModelToUiMapper,
) : PagingSource<Int, RepositoryDataModel>() {

    override fun getRefreshKey(state: PagingState<Int, RepositoryDataModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryDataModel> {
        val nextPageNumber = params.key ?: 1

        val parameters = FetchRepositoryParameters(
            language = "kotlin",
            sort = "",
            page = nextPageNumber,
        )

        return fetchRepositoryUseCase(parameters).map(
            success = {
                LoadResult.Page(
                    data = it.data,
                    prevKey = null, // Only paging forward.
                    nextKey = nextPageNumber + 1
                )
            },
            error = {
                val message = repositoryErrorModelToUiMapper.mapFrom(it)

                LoadResult.Error(IllegalStateException(message))
            }
        )
    }
}
