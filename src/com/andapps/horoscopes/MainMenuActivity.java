package com.andapps.horoscopes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.andapps.horoscopes.model.Horoscope;
import com.andapps.horoscopes.model.ImageAdapter;
import com.andapps.horoscopes.utilisz.Analytics;


public class MainMenuActivity extends Activity implements
		OnMenuItemClickListener {

	private int[] mThumbIds = Horoscope.mThumbIds;
	private String[] names = Horoscope.NAMES;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		// final Context myApp = this;

		// change the height to match parent
		/*
		 * // * An instance of this class will be registered as a JavaScript //
		 * * interface //
		 */
		// class MyJavaScriptInterface {
		//
		// @JavascriptInterface
		// public void showHTML(String html) {
		//
		// Document n = Jsoup.parse(html);
		//
		// new AlertDialog.Builder(myApp).setTitle("HTML")
		// .setMessage(n.text())
		// .setPositiveButton(android.R.string.ok, null)
		// .setCancelable(false).create().show();
		// Log.d("html", n.getElementsByClass("title").text());
		// }
		// }
		//
		// final WebView browser = (WebView) findViewById(R.id.webView1);
		// /* JavaScript must be enabled if you want it to work, obviously */
		// browser.getSettings().setJavaScriptEnabled(true);
		//
		// /* Register a new JavaScript interface called HTMLOUT */
		// browser.addJavascriptInterface(new MyJavaScriptInterface(),
		// "HTMLOUT");
		//
		// /* WebViewClient must be set BEFORE calling loadUrl! */
		// browser.setWebViewClient(new WebViewClient() {
		// @Override
		// public void onPageFinished(WefbView view, String url) {
		// /*
		// * This call inject JavaScript into the page which just finished
		// * loading.
		// */
		// browser.loadUrl("javascript:window.HTMLOUT.showHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
		// }
		//
		// });
		//
		// /* load a web page */
		// browser.loadUrl("https://play.google.com/store/apps/collection/topselling_new_free");

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
				//overridePendingTransition(R.anim.zoom_in, R.anim.bounce);
				startActivity(i);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
	}

	/*****/

	/****/

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
		menu.findItem(R.id.rate_main).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
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
	public boolean onMenuItemClick(MenuItem item) {

		return false;
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}

}