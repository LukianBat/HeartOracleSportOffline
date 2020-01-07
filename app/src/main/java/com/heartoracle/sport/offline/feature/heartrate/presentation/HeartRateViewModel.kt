package com.heartoracle.sport.offline.feature.heartrate.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.heartoracle.sport.offline.core.presentation.eventsdispatcher.EventsDispatcher
import com.heartoracle.sport.offline.core.presentation.eventsdispatcher.EventsDispatcherOwner
import com.heartoracle.sport.offline.core.presentation.viewmodel.BaseViewModel
import com.heartoracle.sport.offline.feature.heartrate.domain.OsmCalculator
import com.heartoracle.sport.offline.feature.heartrate.domain.model.OsmRes
import com.heartoracle.sport.offline.feature.heartrate.domain.usecase.get.GetHeartRateUseCase
import com.heartoracle.sport.offline.feature.heartrate.domain.usecase.get.GetSitHeartRateUseCase
import com.heartoracle.sport.offline.feature.heartrate.domain.usecase.get.GetStandHeartRateUseCase
import com.heartoracle.sport.offline.feature.settings.domain.usecase.get.GetNumberUseCase
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HeartRateViewModel @Inject constructor(
    private val getHeartRateUseCase: GetHeartRateUseCase,
    private val getSitHeartRateUseCase: GetSitHeartRateUseCase,
    private val getStandHeartRateUseCase: GetStandHeartRateUseCase,
    private val getNumberUseCase: GetNumberUseCase,
    private val osmCalculator: OsmCalculator
) : BaseViewModel(), EventsDispatcherOwner<HeartRateViewModel.EventsListener> {
    override val eventsDispatcher: EventsDispatcher<EventsListener> = EventsDispatcher()

    val measureHeartRate = MutableLiveData<String>()
    val zone = MutableLiveData<String>()
    val score = MutableLiveData<String>()

    private var sitHeartRate: Int = 0
    private var standHeartRate: Int = 0
    private var isStandHeartRateChecked = false


    init {
        getHeartRate()
    }

    @SuppressLint("CheckResult")
    private fun getHeartRate() {
        getHeartRateUseCase.getHeartRate().subscribe {
            measureHeartRate.value = it.toString()
            if (it != 0 && isStandHeartRateChecked.not()) {
                startSitMeasure()
            }
        }.addTo(compositeDisposable)
    }

    @SuppressLint("CheckResult")
    fun startSitMeasure() {
        eventsDispatcher.dispatchEvent { toMeasure() }
        isStandHeartRateChecked = true
        getSitHeartRateUseCase.getSitHeartRate().subscribe { value ->
            sitHeartRate = value
            eventsDispatcher.dispatchEvent { toStandHeartRate() }
            Completable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    startStandMeasure()
                }
        }.addTo(compositeDisposable)
    }

    @SuppressLint("CheckResult")
    fun startStandMeasure() {
        eventsDispatcher.dispatchEvent { toMeasure() }
        getStandHeartRateUseCase.getStandHeartRate().subscribe { value ->
            standHeartRate = value
            calculateResult()
        }.addTo(compositeDisposable)
    }

    private fun calculateResult() {
        compositeDisposable.dispose()
        val osmScore = osmCalculator.calculateScore(sitHeartRate, standHeartRate)
        val osmZone = osmCalculator.calculateZone(osmScore)
        score.value = osmScore.toString()
        zone.value = osmZone
        eventsDispatcher.dispatchEvent {
            toResult(
                OsmRes(
                    sitHeartRate,
                    standHeartRate,
                    osmZone,
                    osmScore,
                    getNumberUseCase.number
                )
            )
        }
    }

    interface EventsListener {
        fun toResult(res: OsmRes)
        fun toMeasure()
        fun toStandHeartRate()
    }

}