package ru.koss.atestlib.ui

import android.os.Bundle
import ru.koss.atestlib.ui.base.BaseMvpActivity
import ru.koss.atestlib.ui.base.stubs.MvpPresenterStub
import ru.koss.atestlib.ui.base.stubs.MvpViewStub
import ru.koss.atestlib.ui.routing.Router
import javax.inject.Inject

class SplashActivity : BaseMvpActivity<MvpPresenterStub, MvpViewStub>() {

    override val presenter = MvpPresenterStub()
    override val view = MvpViewStub()

    /**
     * Here AB test modified router will be injected
     * */
    @Inject
    lateinit var router: Router


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        router.navigateMainScreen(this)
    }

}