package com.heartoracle.sport.offline.feature.settings.data.repository.dagger

import com.heartoracle.sport.offline.feature.settings.data.repository.NumberRepository
import com.heartoracle.sport.offline.feature.settings.data.repository.NumberRepositoryImpl
import com.heartoracle.sport.offline.feature.settings.data.datasource.dagger.NumberDataSourceModule
import dagger.Binds
import dagger.Module

@Module(includes = [NumberDataSourceModule::class])
interface NumberRepositoryModule {

    @Binds
    fun bindRepository(impl: NumberRepositoryImpl): NumberRepository
}