package com.example.chuckchat.di

import com.example.chuckchat.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainViewModel> {
        MainViewModel(sendMessageUseCase = get())
    }
}