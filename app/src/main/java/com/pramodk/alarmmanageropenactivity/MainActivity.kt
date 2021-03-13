package com.pramodk.alarmmanageropenactivity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.pramodk.alarmmanageropenactivity.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var alarmManager: AlarmManager
    private var hour = 0
    private var minute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        binding.btnStart.setOnClickListener {
            startAlarm()
        }
        binding.btnCancel.setOnClickListener {
            stopAlarm()
        }

    }
    private fun startAlarm() {


        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this,0,intent,0)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            hour = binding.timePicker.hour
            minute = binding.timePicker.minute
        }
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY,hour)
        calendar.set(Calendar.MINUTE,minute)
        calendar.set(Calendar.MILLISECOND,0)
        calendar.set(Calendar.SECOND,0)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        Toast.makeText(this,"alarm set",Toast.LENGTH_SHORT).show()
    }

    private fun stopAlarm() {
        val intent = Intent(this,AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this,0,intent,0)
        alarmManager.cancel(pendingIntent)
    }


}