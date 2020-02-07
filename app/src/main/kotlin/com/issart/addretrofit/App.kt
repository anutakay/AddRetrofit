package com.issart.addretrofit

import android.app.Application
import com.issart.addretrofit.di.AppComponent
import com.issart.addretrofit.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .withApplication(this)
            .build()
    }

    companion object {
        @JvmStatic
        lateinit var component: AppComponent
    }
}
