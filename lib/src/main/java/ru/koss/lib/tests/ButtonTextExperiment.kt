package ru.koss.lib.tests
/**
* THIS CODE WAS AUTOGENERATED
* YOU CANNOT MODIFY THIS FILE.
*/
enum class ButtonTextExperiment : BaseTest {
A_EXPERIMENT {
override fun getExperimentVariantDescription(): String = "a variant"  
override fun getExperimentValue(): String = "A" 
override fun getButtonColor(): String = "#0000FF" 
override fun getButtonText(): String = "blue button" 
},
B_EXPERIMENT {
override fun getExperimentVariantDescription(): String = "b variant"  
override fun getExperimentValue(): String = "B" 
override fun getButtonColor(): String = "#FF0000" 
override fun getButtonText(): String = "green button" 
},
C_EXPERIMENT {
override fun getExperimentVariantDescription(): String = "c variant"  
override fun getExperimentValue(): String = "C" 
override fun getButtonColor(): String = "#00FF00" 
override fun getButtonText(): String = "red button" 
},
DEF_EXPERIMENT {
override fun getExperimentVariantDescription(): String = "default variant"
override fun getExperimentValue(): String = "DEF"
};
override fun getExperimentKey(): String = "ButtonTextExperiment"  
open fun getButtonColor(): String = "#000000" 
open fun getButtonText(): String = "def button" 
}