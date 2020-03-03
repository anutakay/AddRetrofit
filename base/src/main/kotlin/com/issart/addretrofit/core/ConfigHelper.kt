package com.issart.addretrofit.core

import android.content.Context
import android.content.res.Resources
import android.content.res.Resources.NotFoundException
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.util.*
import javax.inject.Inject

class ConfigHelper @Inject constructor() {

    fun getConfigValue(context: Context, name: String): String {
        Log.d(TAG, "getConfigValue $name")
        val resources: Resources = context.resources
        try {
            val rawResource: InputStream = resources.openRawResource(R.raw.config)
            val properties = Properties()
            properties.load(rawResource)
            return properties.getProperty(name)
        } catch (e: NotFoundException) {
            Log.e(TAG, "Unable to find the config file: ${e.message}")
        } catch (e: IOException) {
            Log.e(TAG, "Failed to open config file.")
        }
        return ""
    }

    companion object {
        private const val TAG = "Helper"
    }
}