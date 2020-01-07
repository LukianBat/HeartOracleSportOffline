package com.heartoracle.sport.offline.feature.heartrate.domain.dagger

import com.heartoracle.sport.offline.feature.heartrate.data.repository.dagger.HeartRateRepositoryModule
import com.heartoracle.sport.offline.feature.heartrate.domain.usecase.get.*
import dagger.Binds
import dagger.Module

@Module(includes = [HeartRateRepositoryModule::class])
interface HeartRateUseCaseModule {

    @Binds
    fun bindGetHeartRateUseCase(impl: GetHeartRateUseCaseImpl): GetHeartRateUseCase

    @Binds
    fun bindGetSitHeartRateUseCase(impl: GetSitHeartRateUseCaseImpl): GetSitHeartRateUseCase

    @Binds
    fun bindGetStandHeartRateUseCase(impl: GetStandHeartRateUseCaseImpl): GetStandHeartRateUseCase
}