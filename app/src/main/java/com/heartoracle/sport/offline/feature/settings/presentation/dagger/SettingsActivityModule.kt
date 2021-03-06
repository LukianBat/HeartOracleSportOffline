package com.heartoracle.sport.offline.feature.settings.presentation.dagger

import androidx.lifecycle.ViewModelProvider
import com.heartoracle.sport.offline.feature.settings.domain.usecase.get.GetNumberUseCase
import com.heartoracle.sport.offline.core.domain.usecase.number.set.SetNumberUseCase
import com.heartoracle.sport.offline.core.presentation.viewmodel.ViewModelFactory
import com.heartoracle.sport.offline.feature.settings.presentation.SettingsActivity
import com.heartoracle.sport.offline.feature.settings.presentation.SettingsViewModel
import dagger.Module
import dagger.Provides

@Module
class SettingsActivityModule {

    @Provides
    fun provideViewModel(
        context: SettingsActivity,
        getNumberUseCase: GetNumberUseCase,
        setNumberUseCase: SetNumberUseCase
    ): SettingsViewModel = ViewModelFactory {
        SettingsViewModel(
            getNumberUseCase,
            setNumberUseCase
        )
    }.let { viewModelFactory ->
        ViewModelProvider(context, viewModelFactory)[SettingsViewModel::class.java]
    }
}