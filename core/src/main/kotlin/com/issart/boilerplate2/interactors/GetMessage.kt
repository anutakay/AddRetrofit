package com.issart.boilerplate2.interactors

import com.issart.boilerplate2.data.repositories.MessageRepository

class GetMessage(private val messageRepository: MessageRepository) {
    operator fun invoke() = messageRepository.getMessage()
}
