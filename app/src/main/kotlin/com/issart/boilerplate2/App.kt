package com.issart.boilerplate2

import android.app.Application
import com.issart.boilerplate2.data.repositories.MessageRepository
import com.issart.boilerplate2.framework.datasource.InMemoryMessageDataSource
import com.issart.boilerplate2.framework.datasource.InMemorySuspendedMessageDataSource
import com.issart.boilerplate2.interactors.GetMessage
import com.issart.boilerplate2.interactors.GetSecondMessage

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val messageRepository = MessageRepository(
            InMemoryMessageDataSource(),
            InMemorySuspendedMessageDataSource()
        )

        AppViewModelFactory.inject(
            this, Interactors(
                GetMessage(messageRepository),
                GetSecondMessage(messageRepository)
            )
        )
    }
}