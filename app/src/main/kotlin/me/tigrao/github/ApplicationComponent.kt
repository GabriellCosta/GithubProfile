package me.tigrao.github

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import de.tigrao.github.domain.profile.di.ProfileModule
import dev.tigrao.github.infra.network.NetworkBuilder
import dev.tigrao.github.infra.network.di.NetworkModule
import me.tigrao.github.repo.di.RepoActivityModule
import javax.inject.Singleton

@Module(
    includes = []
)
internal object AppModule {

    @Singleton
    @Provides
    fun provideNetworkBuilder() = NetworkBuilder(BuildConfig.API_URL)
}

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        RepoActivityModule::class,
        NetworkModule::class,
        ProfileModule::class,
    ],

    )
interface ApplicationComponent {

    fun inject(app: CustomApplication)
}
