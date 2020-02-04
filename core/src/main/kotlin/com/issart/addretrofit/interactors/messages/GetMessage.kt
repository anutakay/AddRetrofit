package com.issart.addretrofit.interactors.messages

import com.issart.addretrofit.data.repositories.MessageRepository

class GetMessage(private val messageRepository: MessageRepository) {
    operator fun invoke() = messageRepository.getMessage()
}
