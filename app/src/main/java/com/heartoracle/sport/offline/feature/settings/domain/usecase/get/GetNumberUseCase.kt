package com.heartoracle.sport.offline.feature.settings.domain.usecase.get

import com.heartoracle.sport.offline.feature.settings.data.repository.NumberRepository
import javax.inject.Inject

interface GetNumberUseCase {
    val number: Int
}

class GetNumberUseCaseImpl @Inject constructor(private val repository: NumberRepository) :
    GetNumberUseCase {
    override val number: Int
        get() = repository.getNumber()
}