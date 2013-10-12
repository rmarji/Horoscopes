package com.andapps.horoscopes.utilis;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;

import com.andapps.horoscopes.MainMenuActivity;

public class Notifications {

//	private static final int APP_ICON = R.drawable.ic_launcher;


	public static void addNotification(Context context, String title,
			String content, int imageRes , Class<?> activityToOpen) {
		addNotification(context, title, content, imageRes, activityToOpen, null);
	}

	@SuppressWarnings("deprecation")
	public static void addNotification(Context context, String title,
			String content, int imageRes , Class<?> activityToOpen, Bundle extraBundle) {

		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(ns);

		
		
		// 2.Instantiate the Notification
		long when = System.currentTimeMillis();
		Notification notification = new Notification(imageRes, title,
				when);
		notification.flags |= Notification.FLAG_ONGOING_EVENT
				| Notification.FLAG_AUTO_CANCEL;

		// 3.Define the Notification's expanded message and Intent:
		CharSequence contentTitle = title;
		CharSequence contentText = content; // message to user
		/*********************/
		// Intent for the activity to open when user selects the notification
		Intent detailsIntent = new Intent(context, activityToOpen);

		// Use TaskStackBuilder to build the back stack and get the PendingIntent
		PendingIntent pendingIntent =
		        TaskStackBuilder.create(context)
		                        // add all of DetailsActivity's parents to the stack,
		                        // followed by DetailsActivity itself
		                        .addNextIntentWithParentStack(new Intent(context, MainMenuActivity.class))
		                        .addNextIntent(detailsIntent)
		                        .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		/****************************/
		//Intent notificationIntent = new Intent(context, activityToOpen);
//		if (extraBundle != null) {
//			notificationIntent.putExtras(extraBundle);
//			notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
//					| Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		}
//
//		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
//				notificationIntent, 0);
		notification.setLatestEventInfo(context, contentTitle, contentText,
				pendingIntent);

		// 4.Pass the Notification to the NotificationManager:
		final int NOTIFICATION_ICON_ID = 1;
		mNotificationManager.notify(NOTIFICATION_ICON_ID, notification);

	}

	// Remove notification
	public static void removeNotification(Context context) {
		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		manager.cancel(1);
	}

	public static void playRingtone(Context context) {
		Uri notification = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		Ringtone r = RingtoneManager.getRingtone(context, notification);
		r.play();
	}
}
