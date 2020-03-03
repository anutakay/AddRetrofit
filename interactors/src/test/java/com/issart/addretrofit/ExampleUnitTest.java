package com.issart.addretrofit;

import com.issart.addretrofit.core.DataResult;
import com.issart.addretrofit.entities.Head;
import com.issart.addretrofit.entities.LanguagesEntity;
import com.issart.addretrofit.entities.LookupEntity;
import com.issart.addretrofit.usecases.lookup.Lookup;
import com.issart.addretrofit.repositories.DictionaryRepository;
import com.issart.addretrofit.repositories.SettingsRepository;

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
        Function1<LookupEntity, Unit> fun1 = (Function1<LookupEntity, Unit>) Mockito.mock(Function1.class);
        Continuation continuation = Mockito.mock(Continuation.class);

        Lookup interactor = new Lookup(dictionary, settings);
        LanguagesEntity lang = new LanguagesEntity("ru", "en", "Русский", "Английский");
        String key = "1";
        String word = "word";
        LookupEntity expected = new LookupEntity(new Head(), new ArrayList<>());

        Mockito.when(settings.getApiKey()).thenReturn(key);
        Mockito.when(dictionary.lookup(key, lang, word, continuation)).thenReturn(new DataResult.Success<>(expected));

        interactor.invoke(lang, word, fun1, continuation);

        Mockito.verify(fun1, Mockito.times(1)).invoke(expected);
    }
}
