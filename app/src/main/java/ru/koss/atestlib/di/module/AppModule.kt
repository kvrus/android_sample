package stetsenko.currencies.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.koss.atestlib.ui.routing.Router
import ru.koss.lib.ABRepository
import ru.koss.lib.ABRepositoryImpl
import ru.koss.lib.config.ABConfigImpl
import stetsenko.currencies.di.scope.ApplicationScope

@Module
class AppModule(context: Context) {
    private val context: Context = context.applicationContext

    @Provides
    @ApplicationScope
    internal fun provideContext(): Context = context

    @Provides
    @ApplicationScope
    internal fun provideRouter(): Router = Router()


    @Provides
    @ApplicationScope
    internal fun provideRepository(): ABRepository {
        val config = ABConfigImpl()
        config.init(context)
        config.setValue("ButtonTextExperiment","A")
        return ABRepositoryImpl("1.0", config)
    }
}