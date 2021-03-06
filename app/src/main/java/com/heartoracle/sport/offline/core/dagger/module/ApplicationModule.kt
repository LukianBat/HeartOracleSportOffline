package com.heartoracle.sport.offline.core.dagger.module

import com.heartoracle.sport.offline.core.dagger.module.feature.HeartRateModule
import com.heartoracle.sport.offline.core.dagger.module.feature.SettingsModule
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        PreferenceModule::class,
        HeartRateSensorModule::class,
        HeartRateModule::class,
        SettingsModule::class]
)
interface ApplicationModule