package com.issart.addretrofit

import android.app.Application
import com.issart.addretrofit.core.di.BaseComponent
import com.issart.addretrofit.core.di.BaseComponentProvider
import com.issart.addretrofit.core.di.DaggerBaseComponent

class BaseApplication : Application(), BaseComponentProvider {

    lateinit var coreComponent: BaseComponent

    override fun provideCoreComponent(): BaseComponent {
        if (!this::coreComponent.isInitialized) {
            coreComponent = DaggerBaseComponent
                .builder()
                .application(this)
                .build()
        }
        return coreComponent
    }
}
