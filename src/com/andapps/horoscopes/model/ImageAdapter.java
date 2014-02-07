package com.andapps.horoscopes.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andapps.horoscopes.R;

public class ImageAdapter extends BaseAdapter {
	private Context context;
	public int[] mThumbIds;
	public String[] names;
	Typeface tf ;

	// Constructor
	public ImageAdapter(Context c, int[] mThumbIds, String[] names) {
		context = c;
		this.mThumbIds = mThumbIds;
		this.names = names;
	}

	@Override
	public int getCount() {
		return mThumbIds.length;
	}

	@Override
	public Object getItem(int position) {
		return mThumbIds[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		tf = Typeface.createFromAsset(context.getAssets(),"fonts/DroidSansArabic.ttf");
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridViewitem;

		if (convertView == null) {

			gridViewitem = new View(context);

			// get layout from main_menu_item.xml
			gridViewitem = inflater.inflate(R.layout.main_menu_item, null);

			
			TextView textView = (TextView) gridViewitem
					.findViewById(R.id.grid_item_label);
			
			ImageView imageView = (ImageView) gridViewitem
					.findViewById(R.id.grid_item_image);

			//set the names and images based on their positions
			imageView.setImageResource(mThumbIds[position]);
			textView.setTypeface(tf);
			textView.setText(names[position]);
			textView.setTextColor(Color.WHITE);
			SharedPreferences sp = context.getSharedPreferences("pref",0);
			if (sp.getInt("hID", 0) == position+1)
			{
				textView.setTextColor(Color.rgb(255,255,102));
				textView.setTextSize(textView.getTextSize()+0.35f);
			}

			

		} else {
			gridViewitem = (View) convertView;
		}

		return gridViewitem;

	}

	// @Override
	// public View getView(int position, View convertView, ViewGroup parent) {
	// ImageView imageView = new ImageView(mContext);
	// imageView.setImageResource(mThumbIds[position]);
	// imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	// imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
	// return imageView;
	// }

}