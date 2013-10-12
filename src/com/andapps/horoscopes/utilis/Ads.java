package com.andapps.horoscopes.utilis;

import android.app.Activity;
import android.widget.LinearLayout;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
//
///* how to initialize ads
// * Define a global variable like this
// * Ads.LosadAds LoadAds;
// * Ads ad = new Ads();;
// * then whenever you need t call ads just do this in the execute
// * LoadAds = ad.new LoadAds(1);
// * LoadAds.execute(ChatActivity.this);
// */
public class Ads {
//
//	private static final String REVMOB_ID = "519420738a868fac30000003";
	public static final String ADMOB_UNIT_ID = "ca-app-pub-2728274842541205/4392626973";
//
//	// TODO: add the ads layout dynamically using java.
//	public static final int REL_LAYOUT = R.id.adrl1;
//
//	//TODO: remove the comment down below
//	//private static final Context CONTEXT = null;
//
//	public RevMob revmob;
//
//	// TODO Change the LoadAds function an make a class for each ad network;
//	// make a random function that chooses between adNetworks
//
	public static void addAdmob(Activity activity) {
		// Create the adView
		AdView adView = new AdView(activity, AdSize.SMART_BANNER, ADMOB_UNIT_ID);
		

		// Lookup your LinearLayout assuming it's been given
		// the attribute android:id="@+id/mainLayout"
		LinearLayout layout = new LinearLayout(activity.getApplicationContext());

		// Add the adView to it
		layout.addView(adView);

		// Initiate a generic request to load it with an ad
		adView.loadAd(new AdRequest());
	}
//
//	public class LoadAds extends AsyncTask<Activity, Void, Void> {
//
//		public boolean FR = false;
//		public int AirPushOrRevMob = 0;
//
//		public LoadAds(boolean firstRun) {
//			FR = firstRun;
//		}
//
//		public LoadAds(int network) {
//			// 1 for AirPush and 2 for RevMob
//			AirPushOrRevMob = network;
//		}
//
//		@Override
//		protected Void doInBackground(Activity... activity) {
//			try {
//				Context context = activity[0].getApplicationContext();
//				if (FR) {
//					// RevMob ------- Start
//					revmob = RevMob.start(activity[0], REVMOB_ID);
//					// RevMob ------- End
//
//					// AdMob ------- Start
//					AdView adView = (AdView) activity[0]
//							.findViewById(REL_LAYOUT);
//					adView.loadAd(new AdRequest());
//					// AdMob ------- End
//
//					// AirPush ------ Start
//					Airpush airpush = new Airpush(context, null);
//					airpush.startPushNotification(false);
//					airpush.startIconAd();
//					// AirPush ------ End
//					return null;
//				}
//				if (AirPushOrRevMob == 1) {
//					Airpush airpush = new Airpush(context, null);
//					airpush.startSmartWallAd();
//					return null;
//				}
//				if (AirPushOrRevMob == 2) {
//					revmob.showFullscreen(activity[0]);
//					return null;
//				}
//
//			} catch (RuntimeException e) {
//
//			}
//			return null;
//		}
//	}
//
//	@Override
//	public void onDismissScreen(Ad arg0) {
//		
//	}
//
//	@Override
//	public void onFailedToReceiveAd(Ad arg0, ErrorCode arg1) {
//		
//	}
//
//	@Override
//	public void onLeaveApplication(Ad arg0) {
//		
//	}
//
//	@Override
//	public void onPresentScreen(Ad arg0) {
//		
//	}
//
//	@Override
//	public void onReceiveAd(Ad arg0) {
//		
//	}

}
