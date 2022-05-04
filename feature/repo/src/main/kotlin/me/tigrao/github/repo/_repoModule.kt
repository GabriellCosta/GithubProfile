package me.tigrao.github.repo

import me.tigrao.github.repo.data.RepoDataSource
import me.tigrao.github.repo.data.RepositoryErrorModelToUiMapper
import me.tigrao.github.repo.data.api.RepoApi
import me.tigrao.github.repo.domain.FetchRepositoryErrorMapper
import me.tigrao.github.repo.domain.FetchRepositorySuccessMapper
import me.tigrao.github.repo.domain.FetchRepositoryUseCase
import me.tigrao.github.repo.domain.FetchRepositoryUseCaseImpl
import me.tigrao.github.repo.presentation.PagerProvider
import me.tigrao.github.repo.presentation.RepoViewModel
import me.tigrao.github.repo.presentation.StateViewFactory
import me.tigrao.github.repo.view.adapter.LayoutManagerFactory
import me.tigrao.github.repo.view.adapter.RepoAdapter
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val repoModule = module {

    single {
        get<Retrofit>().create(RepoApi::class.java)
    }

    single {
        RepoAdapter()
    }

    single {
        RepoDataSource(get(), get())
    }

    factory {
        RepositoryErrorModelToUiMapper(androidContext().resources)
    }

    viewModel {
        RepoViewModel(get(), get())
    }

    factory {
        StateViewFactory(androidContext().resources)
    }

    factory {
        PagerProvider(get())
    }

    single {
        LayoutManagerFactory()
    }

    factory {
        FetchRepositorySuccessMapper()
    }

    factory {
        FetchRepositoryErrorMapper()
    }

    factory<FetchRepositoryUseCase> {
        FetchRepositoryUseCaseImpl(get(), get(), get())
    }
}
