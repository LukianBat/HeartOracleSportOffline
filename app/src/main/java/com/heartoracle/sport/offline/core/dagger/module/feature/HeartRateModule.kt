package com.heartoracle.sport.offline.core.dagger.module.feature

import com.heartoracle.sport.offline.core.dagger.scope.ActivityScope
import com.heartoracle.sport.offline.feature.heartrate.domain.dagger.HeartRateUseCaseModule
import com.heartoracle.sport.offline.feature.heartrate.domain.dagger.OsmCalculatorModule
import com.heartoracle.sport.offline.feature.heartrate.presentation.dagger.HeartRateActivityModule
import com.heartoracle.sport.offline.feature.heartrate.presentation.HeartRateActivity
import com.heartoracle.sport.offline.feature.settings.domain.dagger.NumberUseCaseModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [HeartRateUseCaseModule::class, NumberUseCaseModule::class, OsmCalculatorModule::class])
interface HeartRateModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [HeartRateActivityModule::class])
    fun heartRateActivityInjector(): HeartRateActivity

}