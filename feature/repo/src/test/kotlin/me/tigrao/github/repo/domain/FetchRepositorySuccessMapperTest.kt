package me.tigrao.github.repo.domain

import me.tigrao.github.repo.data.api.model.OwnerResponse
import me.tigrao.github.repo.data.api.model.RepositoriesResponse
import me.tigrao.github.repo.data.api.model.RepositoryItemResponse
import me.tigrao.github.repo.domain.model.RepositoryDataModel
import me.tigrao.github.repo.domain.model.RepositoryModel
import org.junit.Assert.*
import org.junit.Test

class FetchRepositorySuccessMapperTest {

    private val subject = FetchRepositorySuccessMapper()

    @Test
    fun subject_withAllParameter_returnCorrect() {
        val result = subject.mapFrom(
            from = RepositoriesResponse(
                totalCount = 1,
                items = listOf(
                    RepositoryItemResponse(
                        id = 13,
                        name = "mock-name",
                        description = "mock-description",
                        owner = OwnerResponse(
                            avatar = "http://google.com",
                            login = "mock name",
                        ),
                        forks = 31,
                        stars = 445,
                    )
                ),
            )
        )

        val expected = RepositoryModel(
            data = listOf(
                RepositoryDataModel(
                    avatar = "http://google.com",
                    title = "mock-name",
                    author = "mock name",
                    stars = 445,
                    forks = 31,
                    description = "mock-description",
                )
            ),
        )

        assertEquals(expected, result)
    }

    @Test
    fun subject_withNullDescriptionParameter_returnCorrect() {
        val result = subject.mapFrom(
            from = RepositoriesResponse(
                totalCount = 1,
                items = listOf(
                    RepositoryItemResponse(
                        id = 13,
                        name = "mock-name",
                        description = null,
                        owner = OwnerResponse(
                            avatar = "http://google.com",
                            login = "mock name",
                        ),
                        forks = 31,
                        stars = 445,
                    )
                ),
            )
        )

        val expected = RepositoryModel(
            data = listOf(
                RepositoryDataModel(
                    avatar = "http://google.com",
                    title = "mock-name",
                    author = "mock name",
                    stars = 445,
                    forks = 31,
                    description = "",
                )
            ),
        )

        assertEquals(expected, result)
    }
}
