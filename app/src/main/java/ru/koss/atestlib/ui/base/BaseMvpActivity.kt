package ru.koss.atestlib.ui.base;

import android.support.v7.app.AppCompatActivity
import ru.koss.atestlib.App

abstract class BaseMvpActivity<P: MvpPresenter<V>, V: MvpView>: AppCompatActivity(), MvpView {

    protected abstract val presenter: MvpPresenter<V>
    protected abstract val view: V

    protected val appComponent get() = (application as App).appComponent

    override fun onStart() {
        super.onStart()

        presenter.onBind(view)
    }

    override fun onStop() {
        presenter.onUnbind(view)

        super.onStop()
    }
}