package com.andapps.horoscopes.utilis;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

public class ScrapeRanking {

	public static void logRanking(Context c){
	TelephonyManager tm = (TelephonyManager)c.getSystemService(Context.TELEPHONY_SERVICE);
	String countryCode = tm.getSimCountryIso();
	Log.d("country", countryCode);
	}
}
