package com.andapps.horoscopes.utilisz;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class CustomTextView extends TextView {

	// maybe just define a global variable in the application class
	Typeface tf ;
	
	public CustomTextView(Context context) {
		super(context);
		this.setTypeface(tf);

	}
	
	

}
