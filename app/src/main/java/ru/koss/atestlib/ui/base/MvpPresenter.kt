package ru.koss.atestlib.ui.base;


interface MvpPresenter<in V: MvpView> {
    fun onBind(view: V)

    fun onUnbind(view: V)

    fun onFinish()
}