package com.layfones.composewanandroid.account

import com.layfones.composewanandroid.data.services.AccountService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AccountViewModelDelegateModule {

    @Singleton
    @Provides
    fun providerAccountViewModelDelegate(
        service: AccountService,
        accountManager: AccountManager
    ): IAccountViewModelDelegate {
        return AccountViewModelDelegate(service, accountManager)
    }

}