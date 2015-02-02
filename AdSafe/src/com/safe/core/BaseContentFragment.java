package com.safe.core;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public abstract class BaseContentFragment extends BaseFragment {

	public final void setContentFragment(Class<? extends BaseContentFragment> fragmentClass, String tag, Bundle args) {
		if (getActivity() == null) {
			throw new IllegalStateException("error");
		} else {
			BaseActionBarActivity activity = (BaseActionBarActivity) this.getActivity();
			activity.setContentFragment(fragmentClass, tag, args);
		}
	}
	

	public final void setActionBarIndicator() {
		if (getActivity() == null) {
			throw new IllegalStateException("error");
		} else {
			BaseActionBarActivity activity = (BaseActionBarActivity) this.getActivity();
			activity.setActionbarIndicator();
		}
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		Activity activity = getActivity();
		if (activity instanceof BaseActionBarActivity) {
			setActionBarIndicator();
		}
	}
}
