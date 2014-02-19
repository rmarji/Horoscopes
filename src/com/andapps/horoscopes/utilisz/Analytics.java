package com.andapps.horoscopes.utilisz;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.flurry.android.FlurryAgent;

public class Analytics {

	/*
	 * How to use this class 1) Identify your events and even parameters 2)
	 * Create meaningful functions for each event 3) Put the newSession in the
	 * onStart and endSession in onStop
	 */

	// Horoscope read --> parameter horoscope name
	// Horoscope chosen in preferences -- @params Hname
	// GENERIC : feedback given
	// set age, we get it from the welcome screen
	// set gender we get it from the welcome screen
	// Admob pressed to track which segment actually keeps clicking on the ads

	public static final String API_KEY = "3VX7R57GM9N93DTYNB5X";

	/*
	 * this tracks the start of the user using the app should be inserted on the
	 * onStart function in each activity
	 */
	public static void newSession(Context context) {
		FlurryAgent.onStartSession(context, API_KEY);
	}

	/*
	 * this should be inserted in the onStop function override it ends the
	 * session but if another session is created within 10 secs the session is
	 * kept the same.
	 */
	public static void endSession(Context context) {
		FlurryAgent.onEndSession(context);
	}

	/************************ GENERIC Analytics *************************/
	/*
	 * this should be used in the AppRater helper class.
	 * 
	 * @params: takes number of days it took before he finally rated the app
	 */
	public static void appRated(Long days, int launch_count) {
		Map<String, String> convoParams = new HashMap<String, String>();
		convoParams.put("Days_Until_Rated", Long.toString(days));
		convoParams.put("launches_Until_Rated", Integer.toString(launch_count));
		FlurryAgent.logEvent("App_Rated", convoParams);
	}

	/*
	 * this should be used in the AppSharer helper class.
	 * 
	 * @params: takes number of days it took before he finally shared the app
	 * TODO: create AppSharer helper class TODO: a flag needs to be set after he
	 * rates to give him a dialog to share.
	 */
	public static void appShared(int days) {
		Map<String, String> convoParams = new HashMap<String, String>();
		convoParams.put("Days_Until_Shared", Integer.toString(days));
		FlurryAgent.logEvent("App_Shared" , convoParams);
	}

	/*
	 * this function takes the calendar representation of DOB and calculates the
	 * age based on that
	 * 
	 * @params Calendar DOB
	 */
	public static void setAge(Calendar cal) {
		long ageInMS = System.currentTimeMillis() - cal.getTimeInMillis();
		cal.setTimeInMillis(ageInMS);
		int years = cal.get(Calendar.YEAR) - 1970;
		FlurryAgent.setAge(years);
	}

	/*
	 * this function takes number of years and passes it to flurry
	 * 
	 * @params Calendar DOB
	 */
	public static void setAge(int years) {
		FlurryAgent.setAge(years);
	}

	/*************************** CUSTOME Analytics *****************************/
	/*
	 * Stuff to track
	 * 
	 * @1. Users Horoscope.
	 * 
	 * @2. how many horoscopes the user checks per session.
	 * 
	 * @3. what other horoscopes users check after their own this could be
	 * useful to make recommendations to what read next.
	 * 
	 * @4. How many people check the horoscope after receiving the notification.
	 * 
	 * @5. User Preferred Notification time.
	 * 
	 * @6. How long does it take to choose their Dob
	 * 
	 * @7. How many people leave the app on the DOB Choosing
	 * 
	 * @8. How many People skip the DOB Activity
	 */

	public static void setHoro(String horo) {
		Map<String, String> arg1 = new HashMap<String, String>();
		arg1.put("horo", horo);
		FlurryAgent.logEvent("horo_specified", arg1);
	}
	
	public static void horoViewed(String horo) {
		Map<String, String> arg1 = new HashMap<String, String>();
		arg1.put("horo", horo);
		FlurryAgent.logEvent("horo_viewed", arg1 , true);
	}
	
	public static void endHoroViewed(String prof, String love , String health) {
		Map<String, String> arg1 = new HashMap<String, String>();
		arg1.put("prof", prof);
		arg1.put("love", love);
		arg1.put("health", health);
		FlurryAgent.endTimedEvent("horo_viewed",arg1);
	}	
	
	public static void notifClicked(String horo , String dayOfTheWeek , String hour) {
		Map<String, String> arg1 = new HashMap<String, String>();
		arg1.put("horo", horo);
		arg1.put("day_of_the_week", dayOfTheWeek);
		arg1.put("hour_of_the_day", hour);
		FlurryAgent.logEvent("notif_clicked", arg1);
	}

	public static void timeChosen(String horo , String hour , String min) {
		Map<String, String> arg1 = new HashMap<String, String>();
		arg1.put("horo", horo);
		arg1.put("hour_of_the_day", hour);
		arg1.put("min_of_the_hour", min);
		FlurryAgent.logEvent("time_chosen", arg1);
	}
	
	public static void startWizard(String horo , String hour , String min) {
		Map<String, String> arg1 = new HashMap<String, String>();
		arg1.put("horo", horo);
		arg1.put("hour_of_the_day", hour);
		arg1.put("min_of_the_hour", min);
		FlurryAgent.logEvent("time_chosen", arg1);
	}
	
	public static void endWizard(String horo , String hour , String min) {
		Map<String, String> arg1 = new HashMap<String, String>();
		arg1.put("horo", horo);
		arg1.put("hour_of_the_day", hour);
		arg1.put("min_of_the_hour", min);
		FlurryAgent.logEvent("time_chosen", arg1);
	}


}
