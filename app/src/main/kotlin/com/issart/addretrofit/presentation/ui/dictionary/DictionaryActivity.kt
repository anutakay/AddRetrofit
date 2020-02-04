package com.issart.addretrofit.presentation.ui.dictionary

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.issart.addretrofit.presentation.IntentUtil.CHOOSE_INPUT_LANGUAGE
import com.issart.addretrofit.presentation.IntentUtil.CHOOSE_OUTPUT_LANGUAGE
import com.issart.addretrofit.presentation.IntentUtil.chooseInputLanguageIntent
import com.issart.addretrofit.presentation.IntentUtil.chooseOutputLanguageIntent
import com.issart.addretrofit.presentation.ui.SingleFragmentActivity

class DictionaryActivity : SingleFragmentActivity(), DictionaryFragment.InteractionListener {
    override fun createFragment(): Fragment = DictionaryFragment.newInstance()

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
