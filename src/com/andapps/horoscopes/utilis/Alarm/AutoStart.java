package com.andapps.horoscopes.utilis.Alarm;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AutoStart extends BroadcastReceiver
{   
    Alarm alarm = new Alarm();
    @Override
    public void onReceive(Context context, Intent intent)
    {   
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
        	Calendar tc = Calendar.getInstance();
			tc.setTimeInMillis(Alarm.getSameDayAt(System.currentTimeMillis(),
					8, 45) + AlarmManager.INTERVAL_DAY);

			java.text.DateFormat df = SimpleDateFormat.getDateTimeInstance();
			Log.d("date", df.format(tc.getTime()));

			Alarm al = new Alarm();
			al.SetAlarm(context, tc);
        }
    }
}