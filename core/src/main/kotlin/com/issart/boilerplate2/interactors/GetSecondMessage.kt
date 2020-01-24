package com.issart.boilerplate2.interactors

import com.issart.boilerplate2.data.repositories.MessageRepository

class GetSecondMessage(private val messageRepository: MessageRepository) {
    suspend operator fun invoke() = messageRepository.getSecondMessage()
}