package ru.koss.lib.tests
/**
* THIS CODE WAS AUTOGENERATED
* YOU CANNOT MODIFY THIS FILE.
*/
enum class ShowPromoExperiment : BaseTest {
A_EXPERIMENT {
override fun getExperimentVariantDescription(): String = "a variant"  
override fun getExperimentValue(): String = "A" 
override fun shouldBeShown(): String = "false" 
},
DEF_EXPERIMENT {
override fun getExperimentVariantDescription(): String = "default variant"
override fun getExperimentValue(): String = "DEF"
};
override fun getExperimentKey(): String = "ShowPromoExperiment"  
open fun shouldBeShown(): String = "true" 
}