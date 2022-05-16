package com.example.chuckchat.di

import com.example.chuckchat.domaun.domain.usecases.SendMessageUseCase
import org.koin.dsl.module

val domainModule= module {
    factory<SendMessageUseCase> {  SendMessageUseCase(userReository = get())}
}