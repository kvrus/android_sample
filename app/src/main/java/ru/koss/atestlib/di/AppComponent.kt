package stetsenko.currencies.di

import dagger.Component
import ru.koss.atestlib.ui.MainActivity
import ru.koss.atestlib.ui.SplashActivity
import stetsenko.currencies.di.module.AppModule
import stetsenko.currencies.di.module.PresenterModule
import stetsenko.currencies.di.scope.ApplicationScope

@ApplicationScope
@Component(modules = [(AppModule::class), (PresenterModule::class)])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: SplashActivity)
}