package me.tigrao.github.repo.domain

import deb.tigrao.github.infra.api.ResultDomain
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import me.tigrao.github.repo.data.api.RepoApi
import me.tigrao.github.repo.domain.model.RepositoryErrorModel
import me.tigrao.github.repo.domain.model.RepositoryModel
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchRepositoryUseCaseImplTest {

    private val success = mockk<FetchRepositorySuccessMapper>()
    private val error = mockk<FetchRepositoryErrorMapper>()
    private val api = mockk<RepoApi>()

    private val subject = FetchRepositoryUseCaseImpl(api, success, error)

    @Test
    fun invoke_errorApi_callErrorMapper() = runBlocking {
        val parameters = FetchRepositoryParameters(language = "mock-language", "sort", 1)

        val repositoryErrorModel = mockk<RepositoryErrorModel>()

        prepare(
            repositoryErrorModel = repositoryErrorModel,
            apiSuccess = false,
        )

        val result = subject(parameters)

        val expected = ResultDomain.Error(repositoryErrorModel)

        assertEquals(expected, result)
    }

    @Test
    fun invoke_successApi_callSuccessMapper() = runBlocking {
        val parameters = FetchRepositoryParameters(language = "mock-language", "sort", 1)

        val repositoryModel = mockk<RepositoryModel>()

        prepare(
            repositoryModel = repositoryModel,
            apiSuccess = true,
        )

        val result = subject(parameters)

        val expected = ResultDomain.Success(repositoryModel)

        assertEquals(expected, result)
    }

    private fun prepare(
        repositoryErrorModel: RepositoryErrorModel = mockk(),
        repositoryModel: RepositoryModel = mockk(),
        apiSuccess: Boolean = true,
    ) {
        every { error.mapFrom(any()) } returns repositoryErrorModel
        every { success.mapFrom(any()) } returns repositoryModel

        if (apiSuccess) {
            coEvery { api.fetchRepositoriesAsync(any(), any(), any()) } returns mockk()
        } else {
            coEvery {
                api.fetchRepositoriesAsync(
                    any(),
                    any(),
                    any()
                )
            } throws IllegalStateException()
        }
    }
}
