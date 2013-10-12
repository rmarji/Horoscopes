package com.andapps.horoscopes.model;

import com.andapps.horoscopes.R;

public class Horoscope {

	private String id;
	private String date;
	private String daily;
	private String single;
	private String couple;
	private String created_at;

	public static final int[] mThumbIds = { R.drawable.aries,
			R.drawable.taurus, R.drawable.gemini, R.drawable.cancer,
			R.drawable.lion, R.drawable.virgo, R.drawable.libra,
			R.drawable.scorpio, R.drawable.sagittarius, R.drawable.capricorn,
			R.drawable.aquarius, R.drawable.pisces };
	public static final String[] NAMES = { "الحمل", "الثور", "الجوزاء",
		"السرطان", "الأسد", "العذراء", "الميزان", "العقرب", "القوس",
		"الجدي", "الدلو", "الحوت"};

	public static final String[] DATES = { "21/3-20/4", "21/4-20/5",
			"21/5-21/6", "22/6-22/7", "23/7-23/8", "24/8-23/9", "24/9-23/10",
			"24/10-22/11", "23/11-21/12", "22/12-20/1", "21/1-19/2",
			"20/2-20/3" };

	public Horoscope() {

	}

	@Override
	public String toString() {

		return single;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the daily
	 */
	public String getDaily() {
		return daily;
	}

	/**
	 * @param daily
	 *            the daily to set
	 */
	public void setDaily(String daily) {
		this.daily = daily;
	}

	/**
	 * @return the single
	 */
	public String getSingle() {
		return single;
	}

	/**
	 * @param single
	 *            the single to set
	 */
	public void setSingle(String single) {
		this.single = single;
	}

	/**
	 * @return the couple
	 */
	public String getCouple() {
		return couple;
	}

	/**
	 * @param couple
	 *            the couple to set
	 */
	public void setCouple(String couple) {
		this.couple = couple;
	}

	/**
	 * @return the created_at
	 */
	public String getCreated_at() {
		return created_at;
	}

	/**
	 * @param created_at
	 *            the created_at to set
	 */
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}
