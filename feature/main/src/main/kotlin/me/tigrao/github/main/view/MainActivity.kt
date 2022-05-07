package me.tigrao.github.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.hippopotamus.tabarato.designsystem.viewstate.StateViewArg
import by.kirich1409.viewbindingdelegate.viewBinding
import de.tigrao.github.sdu.card.model.CardModel
import dev.tigrao.github.sdu.card.processor.CardProcessor
import me.tigrao.github.main.databinding.ActivityMainBinding
import me.tigrao.github.main.presenter.ProfileContract
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ProfileContract.View {

    internal lateinit var presenter: ProfileContract.Presenter

    @Inject
    internal lateinit var cardProcessor: CardProcessor

    private val binder by viewBinding<MainActivity, ActivityMainBinding> {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //presenter.fetchProfileData()
    }

    override fun setProfileData(cards: List<CardModel>) {
        cardProcessor.process(cards, binder.recycler)
    }

    override fun setErrorState(state: StateViewArg) {
        TODO("Not yet implemented")
    }
}
