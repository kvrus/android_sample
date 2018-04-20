package ru.koss.lib.config

import android.content.Context

/**
 * Created by konstantin on 20.02.18.
 */
interface ABConfig {

    fun init(ctx: Context)
    fun clear()
    fun setValue(key: String, value: String)
    fun getValue(key: String, value: String): String?

}