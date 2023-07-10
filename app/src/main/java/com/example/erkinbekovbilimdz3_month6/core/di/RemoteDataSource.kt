package com.example.erkinbekovbilimdz3_month6.core.di

import com.example.erkinbekovbilimdz3_month6.core.network.RemoteDataSource
import org.koin.dsl.module

val remoteDataSource = module {
    factory { RemoteDataSource(get()) }
}