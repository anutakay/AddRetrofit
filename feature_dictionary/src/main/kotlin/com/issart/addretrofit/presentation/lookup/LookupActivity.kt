package com.issart.addretrofit.presentation.lookup

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.issart.addretrofit.presentation.IntentUtil.CHOOSE_INPUT_LANGUAGE
import com.issart.addretrofit.presentation.IntentUtil.CHOOSE_OUTPUT_LANGUAGE
import com.issart.addretrofit.presentation.IntentUtil.chooseInputLanguageIntent
import com.issart.addretrofit.presentation.IntentUtil.chooseOutputLanguageIntent
import com.issart.addretrofit.core.ui.SingleFragmentActivity

class LookupActivity : SingleFragmentActivity(),
    LookupFragment.InteractionListener {

    override fun createFragment(): Fragment =
        LookupFragment.newInstance()

    override fun chooseInputLanguage() {
        startActivityForResult(chooseInputLanguageIntent(this), CHOOSE_INPUT_LANGUAGE)
    }

    override fun chooseOutputLanguage() {
        startActivityForResult(chooseOutputLanguageIntent(this), CHOOSE_OUTPUT_LANGUAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> supportFragmentManager.fragments.forEach {
                it.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}
