package me.tigrao.github.repo.domain

import deb.tigrao.github.infra.api.ResultDomainError
import me.tigrao.github.repo.domain.model.RepositoryErrorModel
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchRepositoryErrorMapperTest {

    private val subject = FetchRepositoryErrorMapper()

    @Test
    fun mapFrom_unknownError_returnGenericError() {
        val result = subject.mapFrom(
            from = ResultDomainError.UnknownError
        )

        val expected = RepositoryErrorModel.GenericError

        assertEquals(expected, result)
    }

    @Test
    fun mapFrom_networkError_returnGenericError() {
        val result = subject.mapFrom(
            from = ResultDomainError.NetworkError(
                httpCode = 300,
                exceptionTitle = "mock-title",
                httpMessage = "message",
                localizeMessage = "localized message",
                isConnectionError = false,
            )
        )

        val expected = RepositoryErrorModel.GenericError

        assertEquals(expected, result)
    }

    @Test
    fun mapFrom_networkError503_returnMaxOfRequestsReach() {
        val result = subject.mapFrom(
            from = ResultDomainError.NetworkError(
                httpCode = 403,
                exceptionTitle = "mock-title",
                httpMessage = "message",
                localizeMessage = "localized message",
                isConnectionError = false,
            )
        )

        val expected = RepositoryErrorModel.MaxOfRequestReach

        assertEquals(expected, result)
    }
}
