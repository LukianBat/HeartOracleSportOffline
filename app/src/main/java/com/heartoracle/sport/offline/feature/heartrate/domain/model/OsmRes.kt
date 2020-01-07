package com.heartoracle.sport.offline.feature.heartrate.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OsmRes(
    val sitHeartRate: Int,
    val standHeartRate: Int,
    val zone: String,
    val score: Float,
    val id: Int
) : Parcelable