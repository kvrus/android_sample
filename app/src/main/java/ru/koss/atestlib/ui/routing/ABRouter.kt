package ru.koss.atestlib.ui.routing

import android.content.Context
import android.content.Intent
import ru.koss.atestlib.ui.MainActivity

/**
 * Created by konstantin on 20.02.18.
 */
class ABRouter : Router() {

    /**
     * Navigation depends on AB test value
     * */
    override fun navigateMainScreen(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

}