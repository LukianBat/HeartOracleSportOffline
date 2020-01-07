package com.heartoracle.sport.offline.feature.menu

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.heartoracle.sport.offline.R
import com.heartoracle.sport.offline.feature.heartrate.presentation.HeartRateActivity
import com.heartoracle.sport.offline.feature.menu.permission_dialog.PermissionDialog
import com.heartoracle.sport.offline.feature.settings.data.datasource.NumberDataSourceImpl.Companion.NUMBER_DEFAULT
import com.heartoracle.sport.offline.feature.settings.data.datasource.NumberDataSourceImpl.Companion.NUMBER_KEY
import com.heartoracle.sport.offline.feature.settings.presentation.SettingsActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onBackPressed() {}

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissions()
        settingButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        systemSettingsButton.setOnClickListener {
            val intent = Intent(Settings.ACTION_SETTINGS)
            startActivity(intent)
        }
        heartRateButton.setOnClickListener {
            val preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)
            val number = preferences.getInt(NUMBER_KEY, NUMBER_DEFAULT)
            if (number == NUMBER_DEFAULT) {
                Toast.makeText(this, "Установите номер", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, HeartRateActivity::class.java))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun requestPermissions() {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.BODY_SENSORS
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.deniedPermissionResponses.isNotEmpty()) {
                        showPermissionDialog()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).withErrorListener {
                showPermissionDialog()
            }.check()
    }

    private fun showPermissionDialog() {
        PermissionDialog(this).show()
    }

}
