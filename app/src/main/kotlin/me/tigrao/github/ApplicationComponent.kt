package me.tigrao.github

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import de.tigrao.github.domain.profile.di.ProfileModule
import dev.tigrao.github.infra.network.NetworkBuilder
import dev.tigrao.github.infra.network.di.NetworkModule
import dev.tigrao.github.sdu.card.ui.di.CardUiModule
import me.tigrao.github.main.di.MainActivityModule
import javax.inject.Singleton

@Module(
    includes = []
)
internal object AppModule {

    @Singleton
    @Provides
    fun provideNetworkBuilder() = NetworkBuilder(BuildConfig.API_URL, BuildConfig.ACCESS_TOKEN)
}

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        MainActivityModule::class,
        NetworkModule::class,
        ProfileModule::class,
        CardUiModule::class,
    ],

    )
interface ApplicationComponent {

    fun inject(app: CustomApplication)
}
