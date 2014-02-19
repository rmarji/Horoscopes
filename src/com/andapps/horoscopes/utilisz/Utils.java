package com.andapps.horoscopes.utilisz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;



public class Utils {

	/**************************** MEDIA UTILIS **************************/
	public static MediaPlayer SoundPlayer(Context ctx, int raw_id) {
		MediaPlayer player = MediaPlayer.create(ctx, raw_id);
		player.setLooping(false); // Set looping
		player.setVolume(50, 50);
		// player.start();
		return player;
	}

	/**************************** APP UTILIS **************************/
	public static boolean first_boot(Context context) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		if (!prefs.getBoolean("first_time", false)) {
			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean("first_time", true);
			editor.commit();
			return true;
		} else {
			return false;
		}
	}

	/**************************** NETWORK UTILIS **************************/
	public static void postData(List<NameValuePair> p, String SCRIPT_URI) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(SCRIPT_URI);

		try {
			httppost.setEntity(new UrlEncodedFormEntity(p, "UTF-8"));

			HttpResponse response = httpclient.execute(httppost);
			Log.d("response",response.getAllHeaders().toString());
			

		} catch (ClientProtocolException e) {

		} catch (IOException e) {

		}
	}

	public static boolean isNetworkAvailable(Context context) {
		return ((ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE))
				.getActiveNetworkInfo() != null;
	}

	public static void notifyUserIfNotConnected(final Activity activity) {
		// Check for Internet connection ---- Begin
		Context context = activity.getApplicationContext();
		if (!Utils.isNetworkAvailable(context)) {
			new AlertDialog.Builder(context.getApplicationContext())
					.setMessage(
							"Connection ERROR")
					.setCancelable(false)
					.setPositiveButton("EXIT",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {

									activity.finish();
								}
							}).show();
		}
		// Check for Internet connection ---- End

	}

//	@SuppressWarnings("deprecation")
//	public static int getWidth(Context mContext) {
//		int width = 0;
//		WindowManager wm = (WindowManager) mContext
//				.getSystemService(Context.WINDOW_SERVICE);
//		Display display = wm.getDefaultDisplay();
//		if (Build.VERSION.SDK_INT > 12) {
//			Point size = new Point();
//			display.getSize(size);
//			width = size.x;
//		} else {
//			width = display.getWidth(); // deprecated
//		}
//		return width;
//	}
//
//	@SuppressWarnings("deprecation")
//	public static int getHeight(Context mContext) {
//		int height = 0;
//		WindowManager wm = (WindowManager) mContext
//				.getSystemService(Context.WINDOW_SERVICE);
//		Display display = wm.getDefaultDisplay();
//		if (Build.VERSION.SDK_INT > 12) {
//			Point size = new Point();
//			display.getSize(size);
//			height = size.y;
//		} else {
//			height = display.getHeight(); // deprecated
//		}
//		return height;
//	}

	// This will open the Facebook app if the user has it installed. Otherwise,
	// it will open Facebook in the browser.
	public static Intent getOpenFacebookIntent(Context context, String id) {

		try {
			context.getPackageManager()
					.getPackageInfo("com.facebook.katana", 0);
			return new Intent(Intent.ACTION_VIEW,
					Uri.parse("fb://profile/<id_here>"));
		} catch (Exception e) {
			return new Intent(Intent.ACTION_VIEW,
					Uri.parse("https://www.facebook.com/achattie"));
		}
	}

	public static Intent getOpenTwitterIntent(Context context, String id) {
		// TODO: get twitter package name and do this just like the facebook
		// method
		return new Intent(Intent.ACTION_VIEW,
				Uri.parse("https://twitter.com/arabchattie"));
	}

	
	// OBSELETE
	public static String readTwitterFeed() {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet(
				"http://twitter.com/statuses/user_timeline/vogella.json");
		
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e("className", "Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
}
