package me.tigrao.github.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.hippopotamus.tabarato.designsystem.viewstate.StateViewArg
import by.kirich1409.viewbindingdelegate.viewBinding
import de.tigrao.github.sdu.card.model.CardModel
import me.tigrao.github.main.databinding.ActivityMainBinding
import me.tigrao.github.main.presenter.ProfileContract

class MainActivity : AppCompatActivity(), ProfileContract.View {

    private lateinit var presenter: ProfileContract.Presenter

    private val binder by viewBinding<MainActivity, ActivityMainBinding> {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //presenter.fetchProfileData()
    }

    override fun setProfileData(): List<CardModel> {
        TODO("Not yet implemented")
    }

    override fun setErrorState(): StateViewArg {
        TODO("Not yet implemented")
    }
}
