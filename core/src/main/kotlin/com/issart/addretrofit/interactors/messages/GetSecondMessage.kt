package com.issart.addretrofit.interactors.messages

import com.issart.addretrofit.data.repositories.MessageRepository

class GetSecondMessage(private val messageRepository: MessageRepository) {
    suspend operator fun invoke() = messageRepository.getSecondMessage()
}