package com.glicokids.prototype.di

import android.content.Context
import com.glicokids.prototype.data.local.EncryptedStorage
import com.glicokids.prototype.domain.repository.StorageRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    @Singleton
    abstract fun bindStorageRepository(storage: EncryptedStorage): StorageRepository
}
