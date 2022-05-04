package me.tigrao.github

internal object AppInjector {

    fun init(customApplication: CustomApplication) {
        DaggerApplicationComponent.create().inject(customApplication)

        customApplication
            .registerActivityLifecycleCallbacks(ActivityLifecycle())
    }
}
