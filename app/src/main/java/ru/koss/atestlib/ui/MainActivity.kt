package ru.koss.atestlib.ui

import android.os.Bundle
import ru.koss.atestlib.R
import kotlinx.android.synthetic.main.activity_main.*
import ru.koss.atestlib.ui.base.BaseMvpActivity
import ru.koss.atestlib.ui.mvp.MainActivityContract
import javax.inject.Inject

class MainActivity : BaseMvpActivity<MainActivityContract.Presenter, MainActivityContract.View>(), MainActivityContract.View {

    /**
     * Here AB test modified presenter will be injected
     * */
    @Inject
    override lateinit var presenter: MainActivityContract.Presenter

    override val view: MainActivityContract.View get() = this


    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun setButtonText(text: String) {
        hint_button.text = text
    }

    override fun setButtonColor(color: Int) {
        hint_button.setTextColor( color )
    }


}
