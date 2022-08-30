package com.app.myapplication.di

import com.app.myapplication.repo.AppRepo
import com.app.myapplication.repo.AppRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [ApiServiceModule::class])
@InstallIn(SingletonComponent::class)
abstract class MainActivityModules {

    @Binds
    @Singleton
    abstract fun bindWebService(appRepoImpl: AppRepoImpl): AppRepo
}