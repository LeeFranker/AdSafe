package com.safe.view;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.safe.R;
import com.safe.core.BaseActionBarActivity;
import com.safe.fragment.FragmentList;
import com.safe.utils.FragmentTags;

public class ActionBarView implements OnClickListener, OnMenuItemClickListener {

	private Context context;
	private View mActionView;
	private PopupMenu mPopupMenu;

	public ActionBarView(Context context) {
		this.context = context;
		initialize();
	}

	private void initialize() {
		mActionView = View.inflate(context, R.layout.actionbar_view, null);
		ImageView mMoreView = (ImageView) mActionView.findViewById(R.id.more_imageview);
		mMoreView.setOnClickListener(this);
		mPopupMenu = new PopupMenu(context, mMoreView);
		showActionViewIcon(mPopupMenu);
		mPopupMenu.inflate(R.menu.actionbar_menu);
		mPopupMenu.setOnMenuItemClickListener(this);
	}

	public void addActionView(ActionBar actionBar) {
		if (mActionView != null) {
			LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
			lp.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
			actionBar.setDisplayShowCustomEnabled(true);
			actionBar.setCustomView(mActionView, lp);
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.more_imageview:
			mPopupMenu.show();
			break;
		}
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_action_update:
			break;
		case R.id.menu_action_list:
			BaseActionBarActivity list = (BaseActionBarActivity) context;
			list.setContentFragment(FragmentList.class, FragmentTags.FRAGMENT_LIST, null);
			break;
		case R.id.menu_action_feedback:
			break;
		case R.id.menu_action_about:
			break;
		case R.id.menu_action_quit:
			System.exit(0);
			break;
		}
		return true;
	}

	private void showActionViewIcon(PopupMenu popupMenu) {
		try {
			Field[] fields = popupMenu.getClass().getDeclaredFields();
			for (Field field : fields) {
				if ("mPopup".equals(field.getName())) {
					field.setAccessible(true);
					Object menuPopupHelper = field.get(popupMenu);
					Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
					Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
					setForceIcons.invoke(menuPopupHelper, true);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
