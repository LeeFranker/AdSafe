package com.safe.utils;

import android.os.Build;

public class VersionUtils {

	public static boolean hasLowGingerbread() {
		return Build.VERSION.SDK_INT <= 9;
	}

}
