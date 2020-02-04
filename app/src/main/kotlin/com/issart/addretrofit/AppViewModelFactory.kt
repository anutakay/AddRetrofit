package com.issart.addretrofit

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object AppViewModelFactory : ViewModelProvider.Factory {
    lateinit var application: Application
    lateinit var interactors: Interactors

    fun inject(app: Application, dependencies: Interactors) {
        application = app
        interactors = dependencies
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (AppViewModel::class.java.isAssignableFrom(modelClass)) {
            modelClass
                .getConstructor(Application::class.java, Interactors::class.java)
                .newInstance(application, interactors)
        } else {
            throw IllegalStateException("ViewModel must extend AppViewModel")
        }
    }
}