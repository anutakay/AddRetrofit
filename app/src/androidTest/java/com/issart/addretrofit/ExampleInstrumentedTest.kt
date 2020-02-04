package com.issart.addretrofit

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.issart.addretrofit.framework.network.NetworkDictionaryDataSource
import com.issart.addretrofit.framework.network.DictionaryApi
import com.issart.addretrofit.presentation.ui.dictionary.DictionaryActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {

    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.issart.addretrofit", appContext.packageName)
    }

    @Test
    fun testA() {
        activityRule.launchActivity(Intent())
        val retrofit = Retrofit.Builder()
            .baseUrl(AppConfig.endpointUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        runBlocking(Dispatchers.Main) {
            val job = launch(Dispatchers.Main) {
                val dataSource =
                    NetworkDictionaryDataSource(
                        retrofit.create(DictionaryApi::class.java)
                    )
                println(withContext(Dispatchers.IO) {
                    dataSource.lookup(AppConfig.yandexSlovaryApiKey, "en-ru", "lamp")
                })
            }
            job.join()
        }
        activityRule.finishActivity()
    }

    companion object {
        @get:Rule
        val activityRule: ActivityTestRule<DictionaryActivity> =
            ActivityTestRule(DictionaryActivity::class.java, true, false)
    }
}

