package com.pramodk.alarmmanageropenactivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver:BroadcastReceiver() {
    var context:Context? = null
    override fun onReceive(Context: Context, Intent: Intent) {
        val intentActivity = Intent("android.intent.action.sec")
        intentActivity.setClass(Context, SecondActivity::class.java)
        intentActivity.flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK
        this.context = Context
        this.context!!.startActivity(intentActivity)
    }
}