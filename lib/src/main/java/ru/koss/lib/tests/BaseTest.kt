package ru.koss.lib.tests

/**
 * Created by konstantin on 20.02.18.
 */
interface BaseTest {

    fun getDescription() = ""

    fun getExperimentVariantDescription(): String = ""

    fun getExperimentKey(): String = ""

    fun getExperimentValue(): String = ""

}