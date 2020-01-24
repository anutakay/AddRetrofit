package com.issart.boilerplate2.presentation.ui.activities

import androidx.fragment.app.Fragment
import com.issart.boilerplate2.presentation.ui.activities.base.SingleFragmentActivity
import com.issart.boilerplate2.presentation.ui.fragments.SimpleFragment

class SimpleActivity : SingleFragmentActivity(), SimpleFragment.InteractionListener {
    override fun createFragment(): Fragment = SimpleFragment.newInstance()
}
