package com.andapps.horoscopes.utilis;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class AlarmService extends Service
{
	/*
	 * make sure to add needed stuff to the manifest
	 * <receiver
	            android:name="com.andapps.horoscopes.Alarm"
	            android:process=":remote" >
	        </receiver>
	        <receiver android:name="com.andapps.horoscopes.AlarmService" >
	            <intent-filter>
	                <action android:name="android.intent.action.BOOT_COMPLETED" >
	                </action>
	            </intent-filter>
	        </receiver>
	        
	        also wake look permission
	 */
	
    Alarm alarm = new Alarm();
    public void onCreate()
    {
        super.onCreate();       
    }

    public void onStart(Context context,Intent intent, int startId)
    {
//    	 if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
//         {
//             alarm.SetAlarm(context);
//         }
//        alarm.SetAlarm(context);
    }

    @Override
    public IBinder onBind(Intent intent) 
    {
        return null;
    }
}