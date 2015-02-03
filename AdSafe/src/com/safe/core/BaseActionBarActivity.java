package com.safe.core;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;

import com.safe.R;

public abstract class BaseActionBarActivity extends ActionBarActivity {
	private static final String TAG = "adsafe";
	private ActionBar mActionBar;
	private FragmentManager mFragmentManager;
	private FragmentStack mFragmentStack;
	private int mActionbarBgColor;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActionBar = getSupportActionBar();
		mActionbarBgColor = getResources().getColor(R.color.green);
		ColorDrawable drawable = new ColorDrawable(mActionbarBgColor);
		mActionBar.setBackgroundDrawable(drawable);
		mFragmentManager = getSupportFragmentManager();
		mFragmentStack = FragmentStack.forContainer(this, R.id.content_frame, mFragmentManager);
	}

	public final void setContentFragment(Class<? extends BaseFragment> fragment, String tag, Bundle bundle) {
		mFragmentStack.replace(fragment, tag, bundle);
		mFragmentStack.commit();
	}

	public void setActionbarIndicator() {
		if (mFragmentStack != null && mFragmentStack.size() <= 1) {
			Log.d(TAG, "当前是首页");
			mActionBar.setDisplayHomeAsUpEnabled(false);
			mActionBar.setHomeButtonEnabled(false);
		} else {
			Log.d(TAG, "当前不是首页");
			mActionBar.setDisplayHomeAsUpEnabled(true);
			mActionBar.setHomeButtonEnabled(true);
			mActionBar.setDisplayShowHomeEnabled(true);
		}
	}

	protected abstract void initData(Bundle savedInstanceState);

	protected abstract void findViews();

	protected abstract void initViews(Bundle savedInstanceState);

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;
		}
		return super.onOptionsItemSelected(item);

	}

	@Override
	final public void onBackPressed() {
		if (null == mFragmentStack) {
			super.onBackPressed();
			return;
		}
		if (mFragmentStack.size() <= 1) {
			super.onBackPressed();
		} else {
			if (mFragmentStack.peek().onBackPressed()) {
				return;
			} else {
				mFragmentStack.pop();
				return;
			}
		}
	}
}
