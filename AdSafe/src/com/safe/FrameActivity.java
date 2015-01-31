package com.safe;

import android.os.Bundle;

import com.safe.core.BaseActionBarActivity;
import com.safe.fragment.FragmentHome;
import com.safe.utils.Containts;

public class FrameActivity extends BaseActionBarActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_activity);
		setContentFragment(FragmentHome.class, Containts.FRAGMENT_HOME, null);
		findViews();
		initViews(savedInstanceState);
		initData(savedInstanceState);
	}

	@Override
	protected void initData(Bundle savedInstanceState) {

	}

	@Override
	protected void findViews() {

	}

	@Override
	protected void initViews(Bundle savedInstanceState) {

	}

}
