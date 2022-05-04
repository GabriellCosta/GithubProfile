package me.tigrao.github

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dev.tigrao.github.infra.network.di.networkImplModule
import me.tigrao.github.repo.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import javax.inject.Inject


class CustomApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.create().inject(this)

        startKoin {
            // Android context
            androidContext(this@CustomApplication)
            // modules
            modules(repoModule)
            modules(networkImplModule)
            modules(appModule)
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}
