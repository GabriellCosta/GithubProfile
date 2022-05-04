package me.tigrao.github.repo.domain.model

internal sealed interface RepositoryErrorModel {

    object GenericError : RepositoryErrorModel

    object MaxOfRequestReach : RepositoryErrorModel
}
