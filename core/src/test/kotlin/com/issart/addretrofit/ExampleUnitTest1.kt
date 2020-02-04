package com.issart.addretrofit;

import com.issart.addretrofit.data.repositories.DictionaryRepository
import com.issart.addretrofit.data.repositories.SettingsRepository
import com.issart.addretrofit.domain.DataResult
import com.issart.addretrofit.domain.Head
import com.issart.addretrofit.domain.Languages
import com.issart.addretrofit.domain.LookupResult
import com.issart.addretrofit.interactors.lookup.Lookup
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class ExampleUnitTest1 {
    @Test
    fun addition_isCorrect() {
        Assertions.assertThat(2 + 2).isEqualTo(4)
    }

    @Test
    fun lookup_success() {
        val dictionary = Mockito.mock(DictionaryRepository::class.java)
        val settings = Mockito.mock(SettingsRepository::class.java)
        val fun1: Function1<LookupResult, Unit> =
            Mockito.mock(Function1::class.java) as Function1<LookupResult?, Unit>

        val interactor = Lookup(dictionary, settings)
        val lang = Languages("ru", "en", "Русский", "Английский")
        val key = "1"
        val word = "word"
        val expected = LookupResult(Head(), ArrayList())

        runBlocking {
            Mockito.`when`(settings.getApiKey()).thenReturn(key)
            Mockito.`when`<Any>(dictionary.lookup(key, lang, word))
                .thenReturn(DataResult.Success(expected))
            interactor(lang, word, fun1)
        }
        Mockito.verify(fun1, Mockito.times(1))(expected)
    }
}
