package ru.koss.atestlib

import android.app.Application
import stetsenko.currencies.di.AppComponent
import stetsenko.currencies.di.DaggerAppComponent
import stetsenko.currencies.di.module.AppModule

/**
 * Created by konstantin on 20.02.18.
 */

class App : Application() {

    internal lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = buildComponent()
    }

    private fun buildComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(applicationContext))
                .build()
    }



}
