package com.issart.boilerplate2.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.issart.boilerplate2.AppViewModel
import com.issart.boilerplate2.Interactors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SimpleViewModel(app: Application, interactors: Interactors) : AppViewModel(app, interactors) {
    val message: MutableLiveData<String> = MutableLiveData()
    val secondMessage: MutableLiveData<String> = MutableLiveData()

    fun loadMessages() {
        message.postValue(interactors.getMessage())
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                secondMessage.postValue(interactors.getSecondMessage())
            }
        }
    }
}