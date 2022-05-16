package com.example.chuckchat.domaun.domain.usecases

import com.example.chuckchat.domain.repository.UserRepositoryInterface
import com.example.chuckchat.domaun.domain.models.User

class SendMessageUseCase(private val userReository: UserRepositoryInterface) {
    fun exequte(user: User) {
         userReository.sendMessage(user)

    }

}
