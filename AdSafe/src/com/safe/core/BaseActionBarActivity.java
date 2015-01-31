package com.safe.core;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.safe.R;

public abstract class BaseActionBarActivity extends ActionBarActivity {

	private ActionBar mActionBar;
	private FragmentManager mFragmentManager;
	private FragmentStack mFragmentStack;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActionBar = getSupportActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setHomeButtonEnabled(true);
		mActionBar.setDisplayShowHomeEnabled(true);
		int actionBarBgColor = getResources().getColor(R.color.action_bar_bg);
		ColorDrawable drawable = new ColorDrawable(actionBarBgColor);
		mActionBar.setBackgroundDrawable(drawable);
		mFragmentManager = getSupportFragmentManager();
		mFragmentStack = FragmentStack.forContainer(this, R.id.content_frame,
				mFragmentManager);
	}

	public final void setContentFragment(
			Class<? extends BaseFragment> fragment, String tag, Bundle bundle) {
		mFragmentStack.replace(fragment, tag, bundle);
		mFragmentStack.commit();
	}

	public void setActionbarIndicator(boolean home) {

	}

	protected abstract void initData(Bundle savedInstanceState);

	protected abstract void findViews();

	protected abstract void initViews(Bundle savedInstanceState);

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
