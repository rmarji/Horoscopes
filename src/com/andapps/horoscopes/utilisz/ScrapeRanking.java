package com.andapps.horoscopes.utilisz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

public class ScrapeRanking {

	private static final String GDOCS_APP = "entry.1272383851";
	private static final String GDOCS_NET_COUNTRY = "entry.1227628389";
	private static final String GDOCS_SIM_COUNTRY = "entry.1913944977";
	private static final String GDOCS_RANK = "entry.1295679870";

	private static final String SCRIPT_URI = "https://docs.google.com/forms/d/1L0LO6q2RR-28LxvnriLHWoKyTtyjrZj6fgCaaL8t-eg/formResponse";

	public static void logRanking(Context c) {
		TelephonyManager tm = (TelephonyManager) c
				.getSystemService(Context.TELEPHONY_SERVICE);
		String countryCode = tm.getSimCountryIso();
		String contrynetworkcode = tm.getNetworkCountryIso();
		Log.d("country", countryCode);
		Log.d("country", contrynetworkcode);

	}

	Map<String, String> arg1 = new HashMap<String, String>();

	// post to a google doc
	public static void postToAGoogleDoc(ArrayList<NameValuePair> parameters) {
		parameters = new ArrayList<NameValuePair>();

		parameters.add(new BasicNameValuePair(GDOCS_APP, "it"));
		parameters.add(new BasicNameValuePair(GDOCS_NET_COUNTRY, "works"));
		parameters.add(new BasicNameValuePair(GDOCS_SIM_COUNTRY, "perfectly"));
		parameters.add(new BasicNameValuePair(GDOCS_RANK, ":D"));

		// make the request and post the data
		Utils.postData(parameters, SCRIPT_URI);

	}

	public static void getRank(String PackageName) {
		Jsoup.connect("https://play.google.com/store/apps/details?id=com.andapps.horoscopes");

	}

	public static void getInfo(String PackageName) {

	}
}
