package com.example.chuckchat.domain.repository

import com.example.chuckchat.domaun.domain.models.User

interface UserRepositoryInterface {
    fun sendMessage(user:User)

    fun getMessage()
}