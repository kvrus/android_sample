package ru.koss.atestlib.ui.base

abstract class BaseMvpPresenter< V: MvpView>: MvpPresenter<V> {

    protected var view: V? = null

    abstract fun onBindView(view: V)

    override fun onBind(view: V) {
        this.view = view

        onBindView(view)
    }

    override fun onUnbind(view: V) {
        if(this.view === view){
            this.view = null
        }
    }

    override fun onFinish() {
        // release resources if needed
    }
}