package com.issart.addretrofit.presentation.ui.languages

import android.app.Activity
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.issart.addretrofit.LanguagesEntity
import com.issart.addretrofit.presentation.ui.SingleFragmentActivity

class LanguagesActivity : SingleFragmentActivity(), LanguagesFragment.InteractionListener {

    override fun createFragment(): Fragment = LanguagesFragment.newInstance()

    override fun setCustomToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    override fun goToDictionary(lang: LanguagesEntity) {
        println("goToDictionary")
        val intent = Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}