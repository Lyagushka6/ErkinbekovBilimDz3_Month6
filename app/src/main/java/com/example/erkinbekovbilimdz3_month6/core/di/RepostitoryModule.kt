package com.example.erkinbekovbilimdz3_month6.core.di

import com.example.erkinbekovbilimdz3_month6.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single { Repository(get()) }
}