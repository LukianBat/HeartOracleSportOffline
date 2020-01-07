package com.heartoracle.sport.offline.feature.heartrate.domain.dagger

import com.heartoracle.sport.offline.feature.heartrate.domain.OsmCalculator
import com.heartoracle.sport.offline.feature.heartrate.domain.OsmCalculatorImpl
import dagger.Module
import dagger.Provides

@Module
class OsmCalculatorModule() {
    @Provides
    fun provideOsmCalculator(): OsmCalculator = OsmCalculatorImpl()
}