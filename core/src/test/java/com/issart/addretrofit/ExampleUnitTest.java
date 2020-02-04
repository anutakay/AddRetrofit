package com.issart.addretrofit;

import com.issart.addretrofit.data.repositories.DictionaryRepository;
import com.issart.addretrofit.data.repositories.SettingsRepository;
import com.issart.addretrofit.domain.DataResult;
import com.issart.addretrofit.domain.Head;
import com.issart.addretrofit.domain.Languages;
import com.issart.addretrofit.domain.LookupResult;
import com.issart.addretrofit.interactors.lookup.Lookup;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

class ExampleUnitTest {
    @Test
    void addition_isCorrect() {
        Assertions.assertThat(2 + 2).isEqualTo(4);
    }

    @Test
    void lookup_success() {
        DictionaryRepository dictionary = Mockito.mock(DictionaryRepository.class);
        SettingsRepository settings = Mockito.mock(SettingsRepository.class);
        Function1<LookupResult, Unit> fun1 = (Function1<LookupResult, Unit>) Mockito.mock(Function1.class);
        Continuation continuation = Mockito.mock(Continuation.class);

        Lookup interactor = new Lookup(dictionary, settings);
        Languages lang = new Languages("ru", "en", "Русский", "Английский");
        String key = "1";
        String word = "word";
        LookupResult expected = new LookupResult(new Head(), new ArrayList<>());

        Mockito.when(settings.getApiKey()).thenReturn(key);
        Mockito.when(dictionary.lookup(key, lang, word, continuation)).thenReturn(new DataResult.Success<>(expected));

        interactor.invoke(lang, word, fun1, continuation);

        Mockito.verify(fun1, Mockito.times(1)).invoke(expected);
    }
}
