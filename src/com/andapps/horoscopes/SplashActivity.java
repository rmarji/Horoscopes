package com.andapps.horoscopes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.andapps.horoscopes.utilisz.Analytics;

public class SplashActivity extends Activity {

	protected static final int SECONDS_TO_DISPLAY = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_splash);

		Thread timer = new Thread() {
			@Override
			public void run() {
				try {
					sleep(1000*SECONDS_TO_DISPLAY);

				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					showMain();
				}
			}
		};
		timer.start();
	}

	public void showMain() {
		if (getSharedPreferences("pref", 0).getBoolean("show_picker", true))
		{
			startActivity(new Intent(SplashActivity.this,
					PickNotifTimeActivity.class));
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		}
		else
		{
			startActivity(new Intent(SplashActivity.this,
					MainMenuActivity.class));
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		}
		
//	startActivity(new Intent(SplashActivity.this,
//			PickNotifTimeActivity.class));
		finish();
	}

	@Override
	protected void onStart() {
		super.onStart();
		Analytics.newSession(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		Analytics.endSession(this);
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
}
