package com.andapps.horoscopes.utilis.Alarm;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.text.format.DateFormat;
import android.util.Log;

import com.andapps.horoscopes.SingleHoroActivity;
import com.andapps.horoscopes.model.Horoscope;
import com.andapps.horoscopes.utilisz.Notifications;

//TODO: check what the hell is this for the suppress lint wakelook
//TODO: document how to do this appropriately

public class Alarm extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		SharedPreferences sp = context.getSharedPreferences("pref", 0);
		// SharedPreferences.Editor editor =sp.edit();

		Calendar nextTime = Calendar.getInstance();
		// this is set the next alarm the same day today
		nextTime.setTimeInMillis(getSameDayAt(System.currentTimeMillis(), 8,
				45) + AlarmManager.INTERVAL_DAY);

		java.text.DateFormat df = SimpleDateFormat.getDateTimeInstance();

		Log.d("date next", df.format(nextTime.getTime()));

		// nextTime.setTimeInMillis(System.currentTimeMillis()+
		// AlarmManager.INTERVAL_HOUR/60/8);

		//Alarm al = new Alarm();
		//al.SetAlarm(context, nextTime);

		PowerManager pm = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);
		PowerManager.WakeLock wl = pm.newWakeLock(
				PowerManager.PARTIAL_WAKE_LOCK, "");
		wl.acquire();
		wl.release();
		// TODO: try to figure out a way to do the notifications in the
		// compatibility library
		Notifications.addNotification(context,
				Horoscope.NAMES[sp.getInt("hID", 1) - 1],
				"التحديث اليومي لبرجك",
				Horoscope.mThumbIds[sp.getInt("hID", 1) - 1],
				SingleHoroActivity.class);

		// // The PendingIntent will launch activity if the user selects this
		// // notification
		// Intent i = new Intent(context, SingleHoroActivity.class);
		// i.putExtra("yourpackage.notifyId", 1);
		// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// PendingIntent contentIntent = PendingIntent.getActivity(context, 1,
		// intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		//
		// NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
		// context)
		// .setSmallIcon(Horoscope.mThumbIds[sp.getInt("hID", 1) - 1])
		// .setContentTitle(Horoscope.NAMES[sp.getInt("hID", 1) - 1])
		// .setContentText("التحديث اليومي لبرجك")
		// .setContentIntent(contentIntent).setDeleteIntent(contentIntent);
		//
		// playRingtone(context);
		//
		// // Sets an ID for the notification
		// int mNotificationId = 001;
		// // Gets an instance of the NotificationManager service
		// NotificationManager mNotifyMgr = (NotificationManager) context
		// .getSystemService(Context.NOTIFICATION_SERVICE);
		// // Builds the notification and issues it.
		//
		// mNotifyMgr.notify(mNotificationId, mBuilder.build());

		// Toast.makeText(context, "alarm", Toast.LENGTH_SHORT).show();

		//
		/* this is for the notification */
		//
		// String title = "", content = "";
		//
		// // Toast.makeText(context, "got the alarm new",
		// // Toast.LENGTH_LONG).show();
		//
		// // The PendingIntent will launch activity if the user selects this
		// // notification
		// Intent i = new Intent(context, SingleHoroActivity.class);
		// i.putExtra("yourpackage.notifyId", 1);
		// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// PendingIntent contentIntent = PendingIntent.getActivity(context, 1,
		// intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		//
		// Notification noti = new NotificationCompat.Builder(context)
		// .setContentTitle(title).setContentText(content)
		// .setSmallIcon(R.drawable.ic_launcher)
		// .setContentIntent(contentIntent).setDeleteIntent(contentIntent)
		// // .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
		// // R.drawable.a3edited))
		// // .setStyle(new NotificationCompat.BigPictureStyle()
		// // .bigPicture(BitmapFactory.decodeResource(context.getResources(),
		// // R.drawable.a3edited)))
		// .build();
		//
		// // noti.setLatestEventInfo(context,title, content, contentIntent);
		//
		// // Sets an ID for the notification
		// int mNotificationId = 001;
		// // Gets an instance of the NotificationManager service
		// NotificationManager mNotifyMgr = (NotificationManager) context
		// .getSystemService(Context.NOTIFICATION_SERVICE);
		// // Builds the notification and issues it.
		// mNotifyMgr.notify(mNotificationId, noti);
		// playRingtone(context);

		// wl.release();
	}

	public void SetAlarm(Context context) {
		SetAlarm(context, null);
	}

	public void SetAlarm(Context context, Calendar c) {

		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);

		Intent i = new Intent(context, Alarm.class);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);

		if (c == null) {
			c = Calendar.getInstance();
			c.setTimeInMillis(System.currentTimeMillis());
		}

		// am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
		am.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
				AlarmManager.INTERVAL_DAY, pi);
		// Millisec *
		// Second *
		// Minute
		// AlarmManager.INTERVAL_HOUR/60/4

	}

	public static long getSameDayAt(long miliInput, int hour, int min) {
		Calendar c = Calendar.getInstance();

		c.setTimeInMillis(miliInput);
		int year, month, day, sec;
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		sec = 0;

		c.set(year, month, day, hour, min, sec);

		java.text.DateFormat df = SimpleDateFormat.getDateTimeInstance();
		Log.d("date speci", df.format(c.getTime()));

		return c.getTimeInMillis();
	}

	public void CancelAlarm(Context context) {
		Intent intent = new Intent(context, Alarm.class);
		PendingIntent sender = PendingIntent
				.getBroadcast(context, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(sender);
	}

	public static void playRingtone(Context context) {
		Uri notification = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		Ringtone r = RingtoneManager.getRingtone(context, notification);
		r.play();
	}

}