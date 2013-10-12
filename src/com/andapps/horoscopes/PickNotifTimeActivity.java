package com.andapps.horoscopes;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.andapps.horoscopes.model.Horoscope;
import com.andapps.horoscopes.utilis.Alarm;
import com.andapps.horoscopes.utilis.Analytics;
/*
 * changelog
 * v 0.9
 * changed the default time to 10 from 7 based on analytics
 */

@SuppressLint("NewApi")
public class PickNotifTimeActivity extends Activity implements OnClickListener {

	DatePicker dp;

	ImageView iv;
	TextView hLabel;
	Button b;

	@Override
	protected void onStart() {
		Analytics.newSession(this);
		super.onStart();

	}

	@Override
	protected void onStop() {
		Analytics.endSession(this);
		super.onStop();
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_pick_notif_time);

		iv = (ImageView) findViewById(R.id.imageView1);
		hLabel = (TextView) findViewById(R.id.textView2);

		b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(this);

		dp = (DatePicker) findViewById(R.id.datePicker1);
		dp.setOnClickListener(this);

		dp.updateDate(1990, 10, 2);

		dp.setMaxDate(System.currentTimeMillis());
		dp.getCalendarView().setOnDateChangeListener(
				new OnDateChangeListener() {
					@Override
					public void onSelectedDayChange(CalendarView view,
							int year, int month, int dayOfMonth) {

						int hid = getHID(month + 1, dayOfMonth);

						iv.setImageResource(Horoscope.mThumbIds[hid - 1]);
						hLabel.setText(Horoscope.NAMES[hid - 1]);

					}
				});

		// day = dp.getDayOfMonth();
		// month = dp.getMonth();
		// year = dp.getYear();
		//
		// Date dob = new Date(year, month, day);

		// long dobMS = dob.getTime();

	}

	// public static final String[] NAMES = { "الحمل", "الثور", "الجوزاء",
	// "السرطان", "الأسد", "العذراء", "الميزان", "العقرب", "القوس",
	// "الجدي", "الدلو", "الحوت" };
	//
	// public static final String[] DATES = { "21/3-20/4", "21/4-20/5",
	// "21/5-21/6", "22/6-22/7", "23/7-23/8", "24/8-23/9",
	// "24/9-23/10", "24/10-22/11", "23/11-21/12", "22/12-20/1",
	// "21/1-19/2", "20/2-20/3" };
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pick_notif_time, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.datePicker1:

			break;
		case R.id.button1:

			long dobMS;
			int day,
			month,
			hID;

			dobMS = dp.getCalendarView().getDate();

			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(dobMS);

			day = c.get(Calendar.DAY_OF_MONTH);
			month = c.get(Calendar.MONTH) + 1;

			hID = getHID(month, day);

			/* Flurry needs to get the dob directly */
			Analytics.setAge(c);
			Analytics.setHoro(Horoscope.NAMES[hID - 1]);

			SharedPreferences sp = getSharedPreferences("pref", 0);
			SharedPreferences.Editor editor = sp.edit();

			editor.putLong("dob", dobMS);
			editor.putInt("hID", hID);
			editor.putBoolean("showPicker", false);

			editor.apply();

			showPopup(getApplicationContext(), sp.edit());
			break;
		default:
			break;
		}

	}

	int getHID(int month, int day) {
		int hID = 0;

		switch (month) {
		case 1:

			if (day > 0 && day < 21)
				hID = 10;
			else
				hID = 11;
			break;
		case 2:
			if (day > 0 && day < 20)
				hID = 11;
			else
				hID = 12;
			break;

		case 3:
			if (day > 0 && day < 21)
				hID = 12;
			else
				hID = 1;
			break;

		case 4:
			if (day > 0 && day < 21)
				hID = 1;
			else
				hID = 2;
			break;
		case 5:
			if (day > 0 && day < 21)
				hID = 2;
			else
				hID = 3;
			break;

		case 6:
			if (day > 0 && day < 22)
				hID = 3;
			else
				hID = 4;
			break;

		case 7:
			if (day > 0 && day < 23)
				hID = 4;
			else
				hID = 5;
			break;

		case 8:
			if (day > 0 && day < 24)
				hID = 5;
			else
				hID = 6;
			break;

		case 9:
			if (day > 0 && day < 24)
				hID = 6;
			else
				hID = 7;
			break;

		case 10:
			if (day > 0 && day < 24)
				hID = 7;
			else
				hID = 8;
			break;

		case 11:
			if (day > 0 && day < 23)
				hID = 8;
			else
				hID = 9;
			break;

		case 12:
			if (day > 0 && day < 22)
				hID = 9;
			else
				hID = 10;
			break;

		default:
			break;
		}
		return hID;
	}

	public void showPopup(final Context mContext, final Editor editor) {

		TimePickerDialog mTimePicker = new TimePickerDialog(
				PickNotifTimeActivity.this,
				new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker timePicker,
							int selectedHour, int selectedMinute) {

						Analytics.timeChosen(
								Integer.toString(mContext.getSharedPreferences(
										"pref", 0).getInt("hID", -1)),
								Integer.toString(selectedHour),
								Integer.toString(selectedMinute));
						// go to the single horoscope activty and show the saved
						// horo
						/************
						 * this uses the startavtivites() function that add an
						 * activity to the stack using the taskStackBuilder cls
						 *****************/
						TaskStackBuilder tsb = TaskStackBuilder
								.create(getApplicationContext());
						tsb.addNextIntentWithParentStack(
								new Intent(getApplicationContext(),
										MainMenuActivity.class)).addNextIntent(
								new Intent(PickNotifTimeActivity.this,
										SingleHoroActivity.class));
						startActivities(tsb.getIntents());
						/*******************/
						Calendar tc = Calendar.getInstance();
						tc.setTimeInMillis(System.currentTimeMillis());
//						int year = tc.get(Calendar.YEAR);
//						int month = tc.get(Calendar.MONTH);
//						int day = tc.get(Calendar.DAY_OF_MONTH);

						tc.set(Calendar.HOUR_OF_DAY, selectedHour);
						tc.set(Calendar.MINUTE, selectedMinute);

//						Toast.makeText(
//								getApplicationContext(),
//								"year " + year + " month : " + month
//										+ " day : " + day + "  hour : "
//										+ tc.get(Calendar.HOUR_OF_DAY)
//										+ " min : " + tc.get(Calendar.MINUTE),
//								Toast.LENGTH_SHORT).show();
//						tc.set(year, month, day, selectedHour, selectedMinute);

						editor.putLong("notifTime", tc.getTimeInMillis());
						editor.apply();

						Alarm al = new Alarm();
						al.SetAlarm(getApplicationContext(), tc);

					}
				}, 10, 0, false);// Yes 24 hour time
		mTimePicker.setTitle("اختار الوقت المناسب للتنبيه اليومي");
		mTimePicker.show();

	}
}
