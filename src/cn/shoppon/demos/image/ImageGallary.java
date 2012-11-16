/**
 * 
 */
package cn.shoppon.demos.image;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Gallery;
import android.widget.SpinnerAdapter;

/**
 * 
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012. All rights reserved.
 * </p>
 * 
 * @author <a href="www.shoppon.cn">Shoppon</a>
 * @version
 * @see
 */
public class ImageGallary extends Gallery {
	private ImageAdapter mImageAdapter;

	private static final String TAG = "ImageGallary";

	public ImageGallary(Context context) {
		super(context);
	}

	public ImageGallary(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ImageGallary(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, "move");
			break;
		}
		return super.onTouchEvent(event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		final int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, "dispatch move");
			if (mImageAdapter.getCurrentView().isArrivingBorder()) {
				return onTouchEvent(ev);
			}
		}
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public void setAdapter(SpinnerAdapter adapter) {
		mImageAdapter = (ImageAdapter) adapter;
		super.setAdapter(adapter);
	}

}
