package com.safe.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferencesUtils {
	public static final String PREFERENCE_NAME = "SETTING";
	public static final String YOUKU = "YOUKU";
	public static final String TUDOU = "TUDOU";
	public static final String SOUHU = "SOUHU";
	public static final String FENGXING = "FENGXING";
	public static final String PPTV = "PPTV";
	public static final String XUNLEI = "XUNLEI";
	public static final String AIQIYI = "AIQIYI";
	public static final String PPS = "PPS";
	public static final String TENCENT = "TENCENT";

	private static SharedPreferencesUtils instance;
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;

	public static SharedPreferencesUtils getInstance(Context context) {
		if (instance == null)
			instance = new SharedPreferencesUtils(context.getApplicationContext());
		return instance;
	}

	public SharedPreferencesUtils(Context context) {
		if (VersionUtils.hasLowGingerbread()) {
			sp = context.getSharedPreferences(PREFERENCE_NAME, 3);
		} else {
			sp = context.getSharedPreferences(PREFERENCE_NAME, 7);
		}
		editor = sp.edit();
	}

	public long getLongValue(String key) {
		if (key != null && !key.equals("")) {
			return sp.getLong(key, 0);
		}
		return 0;
	}

	public String getStringValue(String key) {
		if (key != null && !key.equals("")) {
			return sp.getString(key, null);
		}
		return null;
	}

	public int getIntValue(String key) {
		if (key != null && !key.equals("")) {
			return sp.getInt(key, 0);
		}
		return 0;
	}

	public boolean getBooleanValue(String key) {
		if (key != null && !key.equals("")) {
			return sp.getBoolean(key, true);
		}
		return true;
	}

	public boolean getBooleanValue(String key, boolean value) {
		if (key != null && !key.equals("")) {
			return sp.getBoolean(key, value);
		}
		return value;
	}

	public boolean getPlayerBooleanValue(String key) {
		if (key != null && !key.equals("")) {
			return sp.getBoolean(key, true);
		}
		return true;
	}

	public boolean getOnPlayingBooleanValue(String key) {
		if (key != null && !key.equals("")) {
			return sp.getBoolean(key, false);
		}
		return false;
	}

	public float getFloatValue(String key) {
		if (key != null && !key.equals("")) {
			return sp.getFloat(key, 0);
		}
		return 0;
	}

	public void putStringValue(String key, String value) {
		if (key != null && !key.equals("")) {
			editor = sp.edit();
			editor.putString(key, value);
		}
	}

	public void putIntValue(String key, int value) {
		if (key != null && !key.equals("")) {
			editor = sp.edit();
			editor.putInt(key, value);
		}
	}

	public void putBooleanValue(String key, boolean value) {
		if (key != null && !key.equals("")) {
			editor = sp.edit();
			editor.putBoolean(key, value);
		}
	}

	public void putPlayerBooleanValue(String key, boolean value) {
		if (key != null && !key.equals("")) {
			editor = sp.edit();
			editor.putBoolean(key, value);
		}
	}

	public void putLongValue(String key, long value) {
		if (key != null && !key.equals("")) {
			editor = sp.edit();
			editor.putLong(key, value);
		}
	}

	public void putFloatValue(String key, Float value) {
		if (key != null && !key.equals("")) {
			editor = sp.edit();
			editor.putFloat(key, value);
		}
	}

	public void remove(String key) {
		if (key != null && !key.equals("")) {
			editor = sp.edit();
			editor.remove(key);
		}
	}



}
