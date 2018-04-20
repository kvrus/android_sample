package ru.koss.atestlib.ui.mvp

import android.graphics.Color
import ru.koss.atestlib.ui.base.BaseMvpPresenter

/**
 * Created by konstantin on 20.02.18.
 */
open class MainActivityPresenter: BaseMvpPresenter<MainActivityContract.View>(), MainActivityContract.Presenter {


    override fun onBindView(view: MainActivityContract.View) {
        view.setButtonText("Normal text")
        view.setButtonColor(Color.BLACK)
    }

    override fun onUnbind(view: MainActivityContract.View) {

    }

    override fun onFinish() {

    }
}