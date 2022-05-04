package me.tigrao.github.repo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.android.AndroidInjection
import de.tigrao.github.domain.profile.GetProfileUseCase
import kotlinx.coroutines.launch
import me.tigrao.github.repo.databinding.ActivityRepoBinding
import javax.inject.Inject

class RepoActivity : AppCompatActivity() {

    private val binder by viewBinding<RepoActivity, ActivityRepoBinding> {
        ActivityRepoBinding.inflate(layoutInflater)
    }

    @Inject
    internal lateinit var useCase: GetProfileUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            useCase("Gabriel")
        }

        /*setContentView(binder.root)

        binder.state.stateViewDispatchAction = this
        prepareList()
        prepareObservers()
        preparePagerCollect()
        prepareStateFlowRead()*/
    }


}
