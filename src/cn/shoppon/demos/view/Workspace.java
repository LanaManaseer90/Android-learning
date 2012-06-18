package cn.shoppon.demos.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class Workspace extends ViewGroup {

	public Workspace(Context context) {
		super(context);
		addView(new MoveViewGroup(context));
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			child.measure(r - l, b - t);
			child.layout(0, 0, 320, 480);
		}
	}

}
