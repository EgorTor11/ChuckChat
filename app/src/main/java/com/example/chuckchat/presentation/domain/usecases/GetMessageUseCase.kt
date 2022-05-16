package com.example.chuckchat.domaun.domain.usecases

import com.example.chuckchat.domain.repository.UserRepositoryInterface

class GetMessageUseCase(private val userRepository:UserRepositoryInterface) {
    fun exequte(){
 userRepository.getMessage()
    }
}