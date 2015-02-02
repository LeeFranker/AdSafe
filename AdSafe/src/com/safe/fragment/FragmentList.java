package com.safe.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.safe.R;
import com.safe.core.BaseContentFragment;

public class FragmentList extends BaseContentFragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_list, container, false);
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
		
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		
	}

}
