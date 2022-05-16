package com.example.chuckchat.di

import com.example.chuckchat.data.repository.UserRepositoryImpl
import com.example.chuckchat.domain.repository.UserRepositoryInterface
import org.koin.dsl.module

val dataModule= module {
    single<UserRepositoryInterface> { UserRepositoryImpl()  }
}