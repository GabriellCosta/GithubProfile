package de.tigrao.github.domain.profile.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import de.tigrao.github.domain.profile.GetProfileDefaultUseCase
import de.tigrao.github.domain.profile.GetProfileUseCase
import de.tigrao.github.domain.profile.data.GetProfileDatasource
import javax.inject.Singleton

@Module(
    includes = [
        ProfileMapperModule::class
    ]
)
internal interface ProfileDomainModule {

    @Singleton
    @Binds
    fun providesGetUserProfileUseCase(bind: GetProfileDefaultUseCase): GetProfileUseCase

    
}
