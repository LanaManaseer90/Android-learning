/**
 * 
 */
package cn.shoppon.demos.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import cn.shoppon.demos.R;

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
public class ZoomImageView extends ImageView {
	private static final String TAG = "ZoomImageView";

	private Bitmap mBitmap;
	private Paint mPaint;
	

	private Rect mSrcRect;
	private Rect mDestRect;

	/** start position **/
	private float mStartX;
	private float mStartY;
	/** end position **/
	private float mEndX;
	private float mEndY;

	private float mOriginDistance;

	private float mZoom;

	public ZoomImageView(Context context) {
		super(context);
	}

	public ZoomImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ZoomImageView(Context context, AttributeSet attrs) {
		super(context, attrs);

		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
		mPaint = new Paint(Paint.FILTER_BITMAP_FLAG);

		mSrcRect = new Rect();
		mDestRect = new Rect();

	}

	public void setBitmap(Bitmap bitmap) {
		this.mBitmap = bitmap;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int bitmapWidth = mBitmap.getWidth();
		int bitmapHeight = mBitmap.getHeight();
		int viewWidth = getWidth();
		int viewHeight = getHeight();

		mSrcRect.left = (int) (bitmapWidth * (mEndX - mStartX) / viewWidth);
		mSrcRect.right = mSrcRect.left + viewWidth;
		mSrcRect.top = (int) (bitmapHeight + (mStartY - mEndY) / viewHeight);
		mSrcRect.bottom = mSrcRect.top + viewHeight;

		mDestRect.left = getLeft();
		mDestRect.top = getTop();
		mDestRect.right = getRight();
		mDestRect.bottom = getBottom();
		canvas.drawBitmap(mBitmap, mSrcRect, mDestRect, mPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		int count = event.getPointerCount();
		if (count == 1) {
			switch (action) {
			case MotionEvent.ACTION_MOVE:
				Log.d(TAG, "action move");
				mEndX = event.getX();
				mEndY = event.getY();
				invalidate();
				break;
			case MotionEvent.ACTION_DOWN:
				Log.d(TAG, "action down");
				mStartX = event.getX();
				mStartY = event.getY();
				break;
			case MotionEvent.ACTION_UP:
				Log.d(TAG, "action up");
				break;
			}
		} else if (count == 2) {
			float x0 = event.getX(event.getPointerId(0));
			float y0 = event.getY(event.getPointerId(0));

			float x1 = event.getX(event.getPointerId(1));
			float y1 = event.getY(event.getPointerId(1));

			float distance = measureDistance(x0, y0, x1, y1);
			switch (action) {
			case MotionEvent.ACTION_MOVE:
				mZoom = distance / mOriginDistance;
				invalidate();
				break;
			case MotionEvent.ACTION_POINTER_1_DOWN:
			case MotionEvent.ACTION_POINTER_2_DOWN:
				mOriginDistance = distance;
				break;
			case MotionEvent.ACTION_POINTER_1_UP:
				break;
			case MotionEvent.ACTION_POINTER_2_UP:
				break;
			}

		}
		return true;
	}

	private float measureDistance(float x0, float y0, float x1, float y1) {
		double dX2 = Math.pow(x0 - x1, 2);
		double dY2 = Math.pow(y1 - y1, 2);
		return (float) Math.pow(dX2 + dY2, 0.5);
	}
}
