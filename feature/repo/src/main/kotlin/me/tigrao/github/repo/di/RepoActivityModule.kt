package me.tigrao.github.repo.di

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import me.tigrao.github.repo.view.RepoActivity

@Module(
    subcomponents = [
        RepoSubComponent::class,
    ]
)
abstract class RepoActivityModule {

    @Binds
    @IntoMap
    @ClassKey(RepoActivity::class)
    abstract fun bindRepoActivity(factory: RepoSubComponent.Factory): AndroidInjector.Factory<*>
}
