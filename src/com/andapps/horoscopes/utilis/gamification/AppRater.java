package com.andapps.horoscopes.utilis.gamification;

import com.andapps.horoscopes.utilisz.Analytics;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

//TODO: maybe add a tip on startup Utilis
public class AppRater {
	private final static String APP_TITLE = "abraj";
	private final static String APP_PNAME = "com.randomchat.rchat";
	private static final CharSequence RATE_LATER_TEXT = "قيَمه لاحقا!";
	private static final CharSequence GIVE_FEEDBACK_TEXT = "او اعطنا اقتراحاتك";
	
	private final static int DAYS_UNTIL_PROMPT = 2;
	private final static int LAUNCHES_UNTIL_PROMPT = 2;
	// TODO: create the feedback Activity class and change it here
	protected static final Class<?> FEEDBACK_ACTIVITY = null;

	

	public static void app_launched(Context mContext) {
		SharedPreferences prefs = mContext.getSharedPreferences("apprater", 0);

		// TODO: remove the comment on the following line
		if (prefs.getBoolean("dontshowagain", false)) {
			return;
		}

		SharedPreferences.Editor editor = prefs.edit();

		// Increment launch counter
		int launch_count = prefs.getInt("launch_count", 0) + 1;
		// TODO: remove next line later
		editor.putInt("launch_count", launch_count);
		android.util.Log.v("launch count", Integer.toString(launch_count));

		// Get date of first launch
		Long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);
		if (date_firstLaunch == 0) {
			date_firstLaunch = System.currentTimeMillis();
			editor.putLong("date_firstlaunch", date_firstLaunch);
		}
		Long days = ((System.currentTimeMillis() - date_firstLaunch) / 86300000);
		Log.d("days",
				Long.toString(days) + "" + Long.toString(date_firstLaunch)
						+ "  " + System.currentTimeMillis());

		// Wait at least n days before opening
		if (launch_count >= LAUNCHES_UNTIL_PROMPT) {
			if (System.currentTimeMillis() >= date_firstLaunch
					+ (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) {
				showRateDialog(mContext, editor);

			}

		}

		editor.commit();
	}

	public static void showRateDialog(final Context mContext,
			final SharedPreferences.Editor editor) {
		final Dialog dialog = new Dialog(mContext);
		dialog.setTitle("تقييم..." + APP_TITLE + " *****");

		LinearLayout ll = new LinearLayout(mContext);
		ll.setOrientation(LinearLayout.VERTICAL);

		TextView tv = new TextView(mContext);
		tv.setText("نتمنى منكم دعم البرنامج وتقييمه "+ APP_TITLE
				+ "");
		tv.setWidth(240);
		tv.setPadding(4, 0, 4, 10);
		ll.addView(tv);

		Button b1 = new Button(mContext);
		b1.setText("Ù‚ÙŠÙ… " + APP_TITLE);
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences prefs = mContext.getSharedPreferences(
						"apprater", 0);
				int launch_count = prefs.getInt("launch_count", 0);
				Long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);
				Long days = ((System.currentTimeMillis() - date_firstLaunch) / 86300000);
				Analytics.appRated(days, launch_count);
				mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse("market://details?id=" + APP_PNAME)));
				dialog.dismiss();
				if (editor != null)
					editor.putBoolean("dontshowagain", true);

			}
		});
		ll.addView(b1);

		Button b2 = new Button(mContext);
		b2.setText(RATE_LATER_TEXT);
		b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				Long date_firstLaunch = System.currentTimeMillis();
				editor.putLong("date_firstlaunch", date_firstLaunch);
			}
		});
		ll.addView(b2);

		if (FEEDBACK_ACTIVITY != null) {
			Button b3 = new Button(mContext);
			b3.setText(GIVE_FEEDBACK_TEXT);
			b3.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intents = new Intent(mContext, FEEDBACK_ACTIVITY);
					mContext.startActivity(intents);
				}
			});
			ll.addView(b3);
		}

		dialog.setContentView(ll);
		dialog.show();
	}

	public static Intent goToMarket() {
		return new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="
				+ APP_PNAME));
	}
}

// see
// http://androidsnippets.com/prompt-engaged-users-to-rate-your-app-in-the-android-market-appirater*/
