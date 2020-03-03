package com.issart.addretrofit.core.di

import android.content.Context

object BaseInjectHelper {
    fun provideBaseComponent(applicationContext: Context): BaseComponent {
        return if (applicationContext is BaseComponentProvider) {
            (applicationContext as BaseComponentProvider).provideCoreComponent()
        } else {
            throw IllegalStateException("The application context you have passed does not implement CoreComponentProvider")
        }
    }
}