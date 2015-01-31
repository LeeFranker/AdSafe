package com.safe.core;

import java.util.Stack;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentStack {

	private Stack<BaseFragment> stack = new Stack<BaseFragment>();
	private Stack<String> tagStack = new Stack<String>();
	private Object lock = new Object();
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;

	private int containerId;
	private FragmentActivity fActivity;
	private Handler handler;

	private final Runnable execPendingTransactions = new Runnable() {
		@Override
		public void run() {
			if (fragmentTransaction != null && fActivity != null) {
				fragmentTransaction.commitAllowingStateLoss();
				fragmentManager.executePendingTransactions();
				fragmentTransaction = null;
			}
		}
	};

	public static FragmentStack forContainer(FragmentActivity activity,
			int containerId, FragmentManager fragmentManager) {
		return new FragmentStack(activity, containerId, fragmentManager);
	}

	private FragmentStack(FragmentActivity fActivity, int containerId,
			FragmentManager fragmentManager) {
		this.fActivity = fActivity;
		this.fragmentManager = fragmentManager;
		this.containerId = containerId;
		handler = new Handler();
	}

	private FragmentTransaction beginTransaction() {
		if (fragmentTransaction == null)
			fragmentTransaction = fragmentManager.beginTransaction();
		handler.removeCallbacks(execPendingTransactions);
		return fragmentTransaction;
	}

	public void replace(Class<? extends BaseFragment> clazz, String tag,
			Bundle args) {
		if (stack.size() > 0) {
			BaseFragment first = stack.firstElement();
			if (first != null && tag.equals(tagStack.firstElement())) {
				if (stack.size() > 1) {
					while (stack.size() > 1) {
						synchronized (lock) {
							stack.pop();
							tagStack.pop();
						}
						fragmentManager.popBackStack();
					}
				}
				return;
			}
			BaseFragment last = stack.peek();
			if (last != null && tag.equals(tagStack.peek())) {
				if (last.isCleanStack()) {
					return;
				}
			}
		}
		BaseFragment fragment = (BaseFragment) fragmentManager
				.findFragmentByTag(tag);
		if (fragment == null) {
			fragment = (BaseFragment) Fragment.instantiate(fActivity,
					clazz.getName(), args);
		}
		if (fragment.isCleanStack()) {
			while (stack.size() > 0) {
				synchronized (lock) {
					stack.pop();
					tagStack.pop();
				}
				fragmentManager.popBackStack();
			}
		}
		attachFragment(fragment, tag);
		synchronized (lock) {
			stack.add(fragment);
			tagStack.add(tag);
		}
	}

	private void attachFragment(Fragment fragment, String tag) {
		if (fragment != null) {
			if (fragment.isDetached()) {
				beginTransaction().attach(fragment);
				if (stack.size() > 0) {
					beginTransaction().addToBackStack(tag);
				}

			} else if (!fragment.isAdded()) {
				beginTransaction().replace(containerId, fragment, tag);
				if (stack.size() > 0) {
					beginTransaction().addToBackStack(tag);
				}
			}
		}
	}

	public BaseFragment peek() {
		return stack.peek();
	}

	public int size() {
		return stack.size();
	}

	public boolean pop() {
		if (stack.size() > 1) {
			synchronized (lock) {
				stack.pop();
				tagStack.pop();
			}
			fragmentManager.popBackStackImmediate();
			return true;
		}
		return false;
	}

	public void commit() {
		if (fragmentTransaction != null && !fragmentTransaction.isEmpty()) {
			handler.removeCallbacks(execPendingTransactions);
			handler.post(execPendingTransactions);
		}
	}

}