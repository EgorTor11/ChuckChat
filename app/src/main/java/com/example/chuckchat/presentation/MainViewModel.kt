package com.example.chuckchat.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.chuckchat.domaun.domain.models.User
import com.example.chuckchat.domaun.domain.usecases.SendMessageUseCase

class MainViewModel(private val sendMessageUseCase: SendMessageUseCase) : ViewModel() {

    fun sendMessage(user:User) {
    sendMessageUseCase.exequte(user)
    }
   // val messages: LiveData<User> = TODO()

}