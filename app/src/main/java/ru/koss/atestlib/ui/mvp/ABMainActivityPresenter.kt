package ru.koss.atestlib.ui.mvp

import android.graphics.Color
import ru.koss.lib.ABRepository
import javax.inject.Inject

/**
 * Created by konstantin on 20.02.18.
 */
class ABMainActivityPresenter @Inject constructor(private val repository: ABRepository): MainActivityPresenter() {

    /**
     * Button appearance depends on AB test value
     * */
    override fun onBindView(view: MainActivityContract.View) {
        view.setButtonText(repository.getButtonTextExperiment().getButtonText())
        view.setButtonColor(Color.parseColor( repository.getButtonTextExperiment().getButtonColor() ))
    }

}