package com.andapps.horoscopes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.andapps.horoscopes.model.Horoscope;
import com.andapps.horoscopes.utilis.Analytics;
import com.andapps.horoscopes.utilis.ImageAdapter;

public class MainMenuActivity extends Activity implements
		OnMenuItemClickListener {

	private int[] mThumbIds = Horoscope.mThumbIds;
	private String[] names = Horoscope.NAMES;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		GridView gv = (GridView) findViewById(R.id.gridView1);
		ImageAdapter ne = new ImageAdapter(this, mThumbIds, names);

		gv.setAdapter(ne);
		gv.setHorizontalSpacing(5);
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) {
				// Sending image id to FullScreenActivity
				Intent i = new Intent(getApplicationContext(),
						SingleHoroActivity.class);
				// Passing array index
				i.putExtra("id", pos);
				startActivity(i);
			}
		});
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		menu.findItem(R.id.rate_main).setIcon(R.drawable.ic_menu_rate);

		return true;

	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		
		return false;
	}
}