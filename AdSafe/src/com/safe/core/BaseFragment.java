package com.safe.core;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public abstract class BaseFragment extends Fragment {

	private String mTitle;
	private FragmentStack mChildStack;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		setMenuVisibility(true);
	}

	protected boolean isCleanStack() {
		return false;
	}

	protected ActionBar getSupportActionBar() {
		Activity activity = getActivity();
		if (activity != null && activity instanceof ActionBarActivity) {
			return ((ActionBarActivity) activity).getSupportActionBar();
		}
		return null;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
		if (getSupportActionBar() != null)
			getSupportActionBar().setTitle(title);
	}

	public void setTitle(int title) {
		this.mTitle = getString(title);
		if (getSupportActionBar() != null)
			getSupportActionBar().setTitle(title);
	}

	protected void initFragmentStack(int containerId) {
		if (null == mChildStack)
			mChildStack = FragmentStack.forContainer(this.getActivity(),
					containerId, this.getChildFragmentManager());
	}

	public boolean onBackPressed() {
		if (null == mChildStack)
			return false;
		if (mChildStack.size() <= 1) {
			return false;
		} else {
			if (mChildStack.peek().onBackPressed()) {
				return true;
			} else {
				mChildStack.pop();
				return true;
			}
		}
	}

	abstract protected void initData(Bundle savedInstanceState);

	abstract protected void findViews(View view);

	abstract protected void initViews(Bundle savedInstanceState);
}
