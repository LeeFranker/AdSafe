package com.safe.fragment;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.safe.R;
import com.safe.core.BaseContentFragment;
import com.safe.view.ActionBarView;

public class FragmentHome extends BaseContentFragment {
	private ActionBar mActionBar;
	private ActionBarView mActionView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		mActionBar = getSupportActionBar();
		mActionBar.setDisplayShowCustomEnabled(false);
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		findViews(view);
		initViews(savedInstanceState);
	}

	@Override
	protected void initData(Bundle savedInstanceState) {

	}

	@Override
	protected void findViews(View view) {
		mActionView = new ActionBarView(getActivity());
		mActionView.addActionView(mActionBar);
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		this.setTitle(R.string.app_name);
	}
}
