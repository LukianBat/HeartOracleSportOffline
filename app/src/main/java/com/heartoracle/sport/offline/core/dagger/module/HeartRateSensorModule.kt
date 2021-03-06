package com.heartoracle.sport.offline.core.dagger.module

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorManager
import dagger.Module
import dagger.Provides

@Module
class HeartRateSensorModule {

    @Provides
    fun provideSensorManager(context: Context): SensorManager =
        context.getSystemService(SENSOR_SERVICE) as SensorManager

    @Provides
    fun provideHeartRateSensor(sensorManager: SensorManager): Sensor =
        sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE)
}