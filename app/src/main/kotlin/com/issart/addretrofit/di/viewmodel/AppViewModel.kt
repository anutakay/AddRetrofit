package com.issart.addretrofit.di.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.issart.addretrofit.App

open class AppViewModel(app: Application) : AndroidViewModel(app) {
    protected val application: App = this.getApplication()
}