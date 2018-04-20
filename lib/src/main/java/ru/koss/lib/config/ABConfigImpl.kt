package ru.koss.lib.config

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by konstantin on 20.02.18.
 */
class ABConfigImpl : ABConfig {

    private lateinit var context: Context

    override fun init(ctx: Context) {
        context = ctx
    }

    override fun clear() {
        get().edit().clear().apply()
    }

    override fun setValue(key: String, value: String) {
        get().edit().putString(key, value).apply()
    }

    override fun getValue(key: String, def: String): String = get().getString(key, def)



    private fun get(): SharedPreferences {
        if(context == null) throw IllegalStateException("You can not use this before initialization, call init() method first")
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

}