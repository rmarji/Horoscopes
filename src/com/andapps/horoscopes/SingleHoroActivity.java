package com.andapps.horoscopes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andapps.horoscopes.model.Horoscope;
import com.andapps.horoscopes.utilis.Ads;
import com.andapps.horoscopes.utilis.Analytics;
import com.andapps.horoscopes.utilis.JSONParser;
import com.andapps.horoscopes.utilis.ScrapeRanking;
import com.andapps.horoscopes.utilis.Utils;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class SingleHoroActivity extends Activity implements TabListener {

	// private static final String URL1 =
	// "http://bublish.me/abraj/android/get_all_horoscopes.php?date=20130610";
	private static final String URL = "http://jogeeks.com/androidapps/abraj/android/get_horoscope.php?hid=";

	private static final String TAG_HOROSCOPES = "horoscopes";
	private static final String TAG_ID = "id";
	private static final String TAG_DATE = "date";
	private static final String TAG_SINGLE = "prof";
	private static final String TAG_DAILY = "love";
	private static final String TAG_COUPLE = "health";
	private static final String TAG_CREATED_AT = "created_at";

	private int[] mThumbIds = Horoscope.mThumbIds;
	private String[] names = Horoscope.NAMES;
	private String[] dates = Horoscope.DATES;

	JSONArray horosopes = null;
	ArrayList<Horoscope> horoscopesObjs;
	int hId;
	Horoscope horoscope;

	// UI
	ImageView hImg;
	ImageView hImg2;
	TextView hDate, hName, horoscopeET;
	View view;

	Typeface tf1, tf2, tf3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_horo);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		/** admob ****/
		AdView adView = new AdView(this, AdSize.SMART_BANNER, Ads.ADMOB_UNIT_ID);
		AdRequest req = new AdRequest();

		req.setBirthday(new Date(getSharedPreferences("pref", 0).getLong("dob",
				0)));
		
		req.addKeyword("ابراج");
		req.addKeyword("horoscope");
		req.addKeyword("Australia");
		req.addKeyword("Forex");		

		// Lookup your LinearLayout assuming it's been given
		// the attribute android:id="@+id/mainLayout"
		// Add the adView to it

		LinearLayout layout = (LinearLayout) findViewById(R.id.sAd);
		layout.addView(adView);

		// Initiate a generic request to load it with an ad
		adView.loadAd(req);
		/**** end admob ***/

		// TODO: change the min to 8 again by adding Sherlok
		// getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		/**** Initialize UI ****/
		hImg = (ImageView) findViewById(R.id.himg);
		hDate = (TextView) findViewById(R.id.hdate);
		hName = (TextView) findViewById(R.id.hname);
		horoscopeET = (TextView) findViewById(R.id.horscope_text);
		hImg2 = (ImageView) findViewById(R.id.ImageView01);
		ImageButton ib = (ImageButton) findViewById(R.id.imageButton1);

		tf1 = Typeface
				.createFromAsset(getAssets(), "fonts/DroidSansArabic.ttf");
		tf2 = Typeface.createFromAsset(getAssets(),
				"fonts/DroidKufi-Regular.ttf");
		tf3 = Typeface.createFromAsset(getAssets(), "fonts/Damascus.ttc");
		/**********************/
		if (getIntent().getExtras() == null) {
			hId = getSharedPreferences("pref", 0).getInt("hID", 1) - 1;

			// TODO: check this to make sure its a ntoifcation thing
			if (!getSharedPreferences("pref", 0).getBoolean("show_picker", true)) {
				Calendar tempc = Calendar.getInstance();
				tempc.setTimeInMillis(System.currentTimeMillis());
				Analytics.notifClicked(Integer.toString(hId),
						Integer.toString(tempc.get(Calendar.DAY_OF_WEEK)),
						Integer.toString(tempc.get(Calendar.HOUR_OF_DAY)));
			}
		} else {

			hId = getIntent().getExtras().getInt("id");
		}

		// TODO: bullet proof the above maybe by figuring out how to add an
		// extra on the notif intent

		hImg.setImageResource(mThumbIds[hId]);
		hImg2.setImageResource(mThumbIds[hId]);
		hDate.setText(dates[hId]);
		hName.setText(names[hId]);
		hDate.setTypeface(tf1);
		hName.setTypeface(tf1);

		Analytics.horoViewed(Horoscope.NAMES[hId]);

		if (Utils.isNetworkAvailable(getApplicationContext())) {
			// get the JSON objects from the link
			new GetJson().execute();
		} else {
			// TODO: add a refresh button
			horoscopeET.setText("الرجاء التاكد من اتصالك بالانترنت");
			Toast.makeText(getApplicationContext(),
					"الرجاء التاكد من اتصالك بالانترنت", Toast.LENGTH_SHORT)
					.show();
		}

		ib.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT, hName.getText() + ": "
						+ horoscopeET.getText() + " http://goo.gl/AO5gZJ");
				startActivity(Intent.createChooser(intent, "انشر برجك"));
			}
		});
	}

	@Override
	protected void onStart() {
		if (getActionBar().getTabCount() == 0) {

			ActionBar.Tab tab = getActionBar().newTab();
			tab.setText("مهنيا");
			tab.setTabListener(SingleHoroActivity.this);
			getActionBar().addTab(tab);

			tab = getActionBar().newTab();
			tab.setText("عاطفيا");
			tab.setTabListener(SingleHoroActivity.this);
			getActionBar().addTab(tab);

			tab = getActionBar().newTab();
			tab.setText("صحياَ");
			tab.setTabListener(SingleHoroActivity.this);
			getActionBar().addTab(tab);

		}
		super.onStart();

		Analytics.newSession(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		Analytics.endSession(this);
	}

	class GetJson extends AsyncTask<String, Void, JSONObject> {

		@Override
		protected JSONObject doInBackground(String... params) {
			String url = URL + (hId + 1);
			Log.d("URL", url);
			JSONObject jsonObj = JSONParser.getJSONFromUrl(url);
			ScrapeRanking.postToAGoogleDoc(new ArrayList<NameValuePair>());
			return jsonObj;

		}

		@Override
		protected void onPostExecute(JSONObject jsonObj) {
			super.onPostExecute(jsonObj);
			// create the list to parse the json into
			horoscopesObjs = new ArrayList<Horoscope>();
			// TODO: activate this to start getting horoscopes
			// ScrapeRanking.logRanking(getApplicationContext());

			try {
				// get the array of objects
				horosopes = jsonObj.getJSONArray(TAG_HOROSCOPES);
				// loop through the horoscopes to get their values into an array
				// list
				for (int i = 0; i < horosopes.length(); i++) {
					// get the obj at the index i
					JSONObject curObj = horosopes.getJSONObject(i);
					// create the horoscope object and set its data then add it
					// to the list
					Horoscope hObj = new Horoscope();
					hObj.setId(curObj.getString(TAG_ID));
					hObj.setDate(curObj.getString(TAG_DATE));
					hObj.setDaily(curObj.getString(TAG_DAILY));
					hObj.setSingle(curObj.getString(TAG_SINGLE));
					hObj.setCouple(curObj.getString(TAG_COUPLE));
					hObj.setCreated_at(curObj.getString(TAG_CREATED_AT));
					horoscopesObjs.add(hObj);
					Log.d("horoscope", hObj.getDaily());

					// hide the progress bar circle
					// TODO: use the support one
					setProgressBarIndeterminateVisibility(false);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			runOnUiThread(new Runnable() {
				public void run() {
					// ListAdapter adapter = new ArrayAdapter<Horoscope>(
					// SingleHoroActivity.this,
					// android.R.layout.simple_list_item_1, horoscopesObjs);
					//
					// ListView lv = (ListView) findViewById(R.id.listView1);
					// lv.setAdapter(adapter);

					horoscopeET.setAnimation(AnimationUtils.loadAnimation(
							getApplicationContext(), R.anim.fade_in));
					horoscopeET.setText(horoscopesObjs.get(0).getSingle());
					horoscopeET.setTypeface(tf3);
					horoscopeET.setTextSize(horoscopeET.getTextSize() * 1.1f);

				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single, menu);
		menu.findItem(R.id.rate).setIcon(R.drawable.ic_menu_rate);
		menu.findItem(R.id.rate).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse("market://details?id=com.andapps.horoscopes")));
				return false;
			}
		});
		return true;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction arg1) {
		Log.d("rese", "true");
		onTabSelected(tab, arg1);

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// LayoutInflater inflater = (LayoutInflater) getApplicationContext()
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// RelativeLayout rl = (RelativeLayout)
		// inflater.inflate(R.layout.main_menu_item, null);
		//
		// TextView text = (TextView) rl.findViewById(R.id.label);
		// ImageView img = (ImageView) rl.findViewById(R.id.grid_item_image);
		//
		// // text.setText("مهنيا");
		// img.setImageResource(Horoscope.mThumbIds[hId]);
		//
		view = tab.getCustomView();

		tab.setCustomView(hImg2);

		if (horoscopesObjs != null) {
			TextView tv = (TextView) findViewById(R.id.horscope_text);
			if (tab.getText().toString().equals("مهنيا")) {

				tv.setAnimation(AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.fade_in));
				tv.setText(horoscopesObjs.get(0).getSingle());
				tv.setTypeface(tf3);

			} else if (tab.getText().toString().equals("عاطفيا")) {
				tv.setAnimation(AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.fade_in));
				tv.setText(horoscopesObjs.get(0).getDaily());
				tv.setTypeface(tf3);
			} else if (tab.getText().toString().equals("صحياَ")) {
				tv.setText(horoscopesObjs.get(0).getCouple());
				tv.setAnimation(AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.fade_in));
				tv.setTypeface(tf3);
			}
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction arg1) {
		tab.setCustomView(view);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
	
	 @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 if (item.getItemId() == android.R.id.home)
			 onBackPressed();
		 return super.onOptionsItemSelected(item);
	}
}
