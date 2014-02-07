package com.andapps.horoscopes.utilis.gamification;
//package com.andapps.horoscopes;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager.NameNotFoundException;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.flurry.android.FlurryAgent;
//
//public class FeedbackActivity extends Activity {
//
//        private static final String SCRIPT_URI = "https://docs.google.com/forms/d/1uTeqTU8FBquMknF4w8MUEKMfJezKsqGf_nqdsNqDVfs/formResponse";
//        private static final String GDOCS_NAME = "entry.1317365715";
//        private static final String GDOCS_EMAIL = "entry.294533534";
//        private static final String GDOCS_FEEDBACK = "entry.1023248147";
//        private static final String GDOCS_VERSION = "entry.524078501";
//        private static final String GDOCS_MANUFACTURER = "entry.775552284";
//        private static final String GDOCS_MODEL = "entry.186501150";
//        private static final String GDOCS_ANDROID_VERSION = "entry.1465345403";
//        private static final String GDOCS_DISPLAY = "entry.972976949";
//        public PackageInfo vInfo;
//
//        EditText name, email, feedback;
//        List<NameValuePair> parameters;
//
//        String Manufacturer = "unknown";
//        String Model = "unknown";
//        String AndroidVersion = "unknown";
//        String Display = "unknown";
//        String Version;
//
//        // TODO: change this to ASync task because its best practice
//        Thread t = new Thread(new Runnable() {
//
//                @Override
//                public void run() {
//                        // get the parameters
//                        parameters = new ArrayList<NameValuePair>();
//
//                        parameters.add(new BasicNameValuePair(GDOCS_NAME, name.getText()
//                                        .toString()));
//                        parameters.add(new BasicNameValuePair(GDOCS_EMAIL, email.getText()
//                                        .toString()));
//                        parameters.add(new BasicNameValuePair(GDOCS_FEEDBACK, feedback
//                                        .getText().toString()));
//                        parameters.add(new BasicNameValuePair(GDOCS_VERSION, Version));
//
//                        parameters.add(new BasicNameValuePair(GDOCS_MANUFACTURER,
//                                        Manufacturer));
//                        parameters.add(new BasicNameValuePair(GDOCS_MODEL, Model));
//                        parameters.add(new BasicNameValuePair(GDOCS_ANDROID_VERSION,
//                                        AndroidVersion));
//                        parameters.add(new BasicNameValuePair(GDOCS_DISPLAY, Display));
//
//                        // make the request and post the data
//                        Utils.postData(parameters, SCRIPT_URI);
//
//                        // go back
//                        Intent i = new Intent(FeedbackActivity.this, MainMenuActivity.class);
//                        startActivity(i);
//
//                }
//        });
//
//        @Override
//        public void onBackPressed() {
//                Intent i = new Intent(FeedbackActivity.this, MainMenuActivity.class);
//                startActivity(i);
//                return;
//        }
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//                super.onCreate(savedInstanceState);
//                setContentView(R.layout.activity_feedback);
//
////                final Button submit = (Button) findViewById(R.id.submit);
////
////                name = (EditText) findViewById(R.id.etname);
////                email = (EditText) findViewById(R.id.etemail);
////                feedback = (EditText) findViewById(R.id.feedback);
////                try {
////                        Version = this.getPackageManager().getPackageInfo(
////                                        this.getPackageName(), 0).versionName;
////                } catch (NameNotFoundException e) {
////                        // TODO Auto-generated catch block
////                        e.printStackTrace();
////                }
////
////                // TODO add to Utils
////                Manufacturer = Build.BRAND;
////                Model = Build.MODEL;
////                AndroidVersion = Build.VERSION.RELEASE;
////                Display = Integer.toString(Utils.getHeight(this)) + "X"
////                                + Integer.toString(Utils.getWidth(this));
//
//                submit.setOnClickListener(new OnClickListener() {
//
//                        @Override
//                        public void onClick(View v) {
//                                if (feedback.getText().toString().equals("")) {
//                                        new AlertDialog.Builder(FeedbackActivity.this)
//                                                        .setMessage("الرجاء التاكد من حقل الاقتراح")
//                                                        .setCancelable(false)
//                                                        .setPositiveButton("حسناً",
//                                                                        new DialogInterface.OnClickListener() {
//                                                                                @Override
//                                                                                public void onClick(
//                                                                                                DialogInterface dialog,
//                                                                                                int which) {
//                                                                                        dialog.cancel();
//                                                                                }
//                                                                        }).show();
//                                } else {
//                                        submit.setEnabled(false);
//                                        t.start();
//
//                                        FlurryAgent.logEvent("Feedback Sent");
//
//                                        Toast.makeText(FeedbackActivity.this,
//                                                        "لقد تم ارسال الاقتراح...شكرا!", Toast.LENGTH_SHORT)
//                                                        .show();
//                                }
//                        }
//                });
//        }
//
//        @Override
//        public boolean onCreateOptionsMenu(Menu menu) {
//                // Inflate the menu; this adds items to the action bar if it is present.
//                // MenuInflater inflater = getSupportMenuInflater();
//
//                return true;
//        }
//
//}