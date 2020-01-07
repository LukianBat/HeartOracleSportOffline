package com.heartoracle.sport.offline.feature.result

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Point
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.heartoracle.sport.offline.R
import com.heartoracle.sport.offline.feature.heartrate.domain.model.OsmRes
import com.heartoracle.sport.offline.feature.menu.MainActivity
import kotlinx.android.synthetic.main.activity_result.*


class ResultActivity : AppCompatActivity() {

    private lateinit var results: OsmRes
    private var isQrSwitch = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        results = intent.getParcelableExtra(RESULT_KEY) as OsmRes
        val builder = GsonBuilder()
        val gson: Gson = builder.create()
        textScore.text = results.score.toString()
        textZone.text = results.zone
        qrImage.setImageBitmap(getQR(gson.toJson(results)))
        mainButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        switchView.setOnClickListener {
            if (isQrSwitch) {
                switchTextView.text = switchQRText
                resultView.visibility = View.VISIBLE
                qrView.visibility = View.GONE
                isQrSwitch = false
            } else {
                resultView.visibility = View.GONE
                qrView.visibility = View.VISIBLE
                switchTextView.text = switchResultText
                isQrSwitch = true
            }
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun getQR(string: String): Bitmap {
        val manager =
            getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = manager.defaultDisplay
        val point = Point()
        display.getSize(point)
        val width: Int = point.x
        val height: Int = point.y
        var smallerDimension = if (width < height) width else height
        smallerDimension = smallerDimension * 3 / 4
        return QRGEncoder(
            string, null,
            QRGContents.Type.TEXT,
            smallerDimension
        ).encodeAsBitmap()
    }

    companion object {
        const val RESULT_KEY = "RESULTS"
        const val switchQRText = "Показать\nQR"
        const val switchResultText = "Показать результат"
    }
}

