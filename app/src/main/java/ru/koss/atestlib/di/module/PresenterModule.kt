package stetsenko.currencies.di.module

import dagger.Module
import dagger.Provides
import ru.koss.atestlib.ui.mvp.ABMainActivityPresenter
import ru.koss.atestlib.ui.mvp.MainActivityContract
import stetsenko.currencies.di.scope.ApplicationScope

@Module
class PresenterModule {

    @Provides
    @ApplicationScope
    fun bindCategorizationPresenter(presenter: ABMainActivityPresenter): MainActivityContract.Presenter = presenter

}