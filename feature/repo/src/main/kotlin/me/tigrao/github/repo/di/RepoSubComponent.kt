package me.tigrao.github.repo.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import me.tigrao.github.repo.view.RepoActivity

@Subcomponent
interface RepoSubComponent: AndroidInjector<RepoActivity> {
    @Subcomponent.Factory
    interface Factory: AndroidInjector.Factory<RepoActivity>
}
