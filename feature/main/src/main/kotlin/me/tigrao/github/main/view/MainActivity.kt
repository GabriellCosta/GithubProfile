package me.tigrao.github.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import de.tigrao.github.domain.profile.GetProfileUseCase
import kotlinx.coroutines.launch
import me.tigrao.github.main.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binder by viewBinding<MainActivity, ActivityMainBinding> {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    internal lateinit var useCase: GetProfileUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            useCase("Gabriel")
        }
    }
}
