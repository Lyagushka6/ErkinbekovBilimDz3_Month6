package com.example.erkinbekovbilimdz3_month6.core.di

import com.example.erkinbekovbilimdz3_month6.core.network.networkModule

val koinModules = listOf(
    networkModule,
    repositoryModule,
    viewModules,
    remoteDataSource,
)