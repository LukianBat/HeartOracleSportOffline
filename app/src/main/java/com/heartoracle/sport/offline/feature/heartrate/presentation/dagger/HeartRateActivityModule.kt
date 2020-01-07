package com.heartoracle.sport.offline.feature.heartrate.presentation.dagger

import androidx.lifecycle.ViewModelProvider
import com.heartoracle.sport.offline.feature.settings.domain.usecase.get.GetNumberUseCase
import com.heartoracle.sport.offline.core.presentation.viewmodel.ViewModelFactory
import com.heartoracle.sport.offline.feature.heartrate.domain.OsmCalculator
import com.heartoracle.sport.offline.feature.heartrate.domain.usecase.get.GetHeartRateUseCase
import com.heartoracle.sport.offline.feature.heartrate.domain.usecase.get.GetSitHeartRateUseCase
import com.heartoracle.sport.offline.feature.heartrate.domain.usecase.get.GetStandHeartRateUseCase
import com.heartoracle.sport.offline.feature.heartrate.presentation.HeartRateActivity
import com.heartoracle.sport.offline.feature.heartrate.presentation.HeartRateViewModel
import dagger.Module
import dagger.Provides

@Module
class HeartRateActivityModule {

    @Provides
    fun provideViewModel(
        context: HeartRateActivity,
        getHeartRateUseCase: GetHeartRateUseCase,
        getSitHeartRateUseCase: GetSitHeartRateUseCase,
        getStandHeartRateUseCase: GetStandHeartRateUseCase,
        getNumberUseCase: GetNumberUseCase,
        osmCalculator: OsmCalculator
    ): HeartRateViewModel = ViewModelFactory {
        HeartRateViewModel(
            getHeartRateUseCase,
            getSitHeartRateUseCase,
            getStandHeartRateUseCase,
            getNumberUseCase,
            osmCalculator
        )
    }.let { viewModelFactory ->
        ViewModelProvider(context, viewModelFactory)[HeartRateViewModel::class.java]
    }
}