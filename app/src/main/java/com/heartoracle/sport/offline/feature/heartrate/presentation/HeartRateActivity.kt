package com.heartoracle.sport.offline.feature.heartrate.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.view.WindowManager
import com.heartoracle.sport.offline.BR
import com.heartoracle.sport.offline.R
import com.heartoracle.sport.offline.core.presentation.activity.EventsActivity
import com.heartoracle.sport.offline.databinding.ActivityHeartrateBinding
import com.heartoracle.sport.offline.feature.heartrate.domain.model.OsmRes
import com.heartoracle.sport.offline.feature.result.ResultActivity
import kotlinx.android.synthetic.main.activity_heartrate.*
import javax.inject.Inject


class HeartRateActivity :
    EventsActivity<ActivityHeartrateBinding, HeartRateViewModel, HeartRateViewModel.EventsListener>(),
    HeartRateViewModel.EventsListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun onBackPressed() {

    }

    override fun toResult(res: OsmRes) {
        val resultIntent = Intent(this, ResultActivity::class.java)
        resultIntent.putExtra("RESULTS", res)
        startActivity(resultIntent)
    }

    override fun toStandHeartRate() {
        measureView.visibility = View.GONE
        imageOsm.setImageResource(R.drawable.ic_stand)
        imageOsm.visibility = View.VISIBLE
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(500)
    }

    override fun toMeasure(sit: Boolean) {
        if (sit) {
            miniIcon.setImageResource(R.drawable.ic_sit)
        } else {
            miniIcon.setImageResource(R.drawable.ic_stand)
        }
        imageOsm.visibility = View.GONE
        measureView.visibility = View.VISIBLE
    }

    override val eventsListener: HeartRateViewModel.EventsListener = this

    override val viewModelVariableId: Int = BR.viewModel

    @Inject
    override lateinit var viewModel: HeartRateViewModel

    override val layoutId = R.layout.activity_heartrate

}