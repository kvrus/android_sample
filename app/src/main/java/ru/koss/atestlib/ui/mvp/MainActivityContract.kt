package ru.koss.atestlib.ui.mvp

import ru.koss.atestlib.ui.base.MvpPresenter
import ru.koss.atestlib.ui.base.MvpView

/**
 * Created by konstantin on 20.02.18.
 */
interface MainActivityContract {

    interface View: MvpView {
        fun setButtonText(text: String)
        fun setButtonColor(color: Int)
    }

    interface Presenter: MvpPresenter<View>

}