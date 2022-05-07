package me.tigrao.github.main.domain.model

internal sealed interface UserProfileUiErrorModel {

    object GenericError : UserProfileUiErrorModel
}
