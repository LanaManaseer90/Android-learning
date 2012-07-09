package cn.shoppon.demos.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class ScaleImageView extends ImageView {
	private float MAX_SCALE = 4f;

	private Matrix mMatrix;
	private final float[] mMatrixValues = new float[9];

	private int mWidth;
	private int mHeight;

	private int mIntrinsicWidth;
	private int mIntrinsicHeight;

	private float mScale;
	private float mMinScale;

	private float mPrevDistance;
	private boolean isScaling;
	private boolean isArrivingBorder = true;

	private int mPrevMoveX;
	private int mPrevMoveY;

	String TAG = "ScaleImageView";

	public ScaleImageView(Context context, AttributeSet attr) {
		super(context, attr);
		initialize();
	}

	public ScaleImageView(Context context) {
		super(context);
		initialize();
	}

	@Override
	public void setImageBitmap(Bitmap bm) {
		super.setImageBitmap(bm);
		this.initialize();
	}

	private void initialize() {
		this.setScaleType(ScaleType.MATRIX);
		this.mMatrix = new Matrix();
		Drawable d = getDrawable();
		if (d != null) {
			mIntrinsicWidth = d.getIntrinsicWidth();
			mIntrinsicHeight = d.getIntrinsicHeight();
		}
	}

	@Override
	protected boolean setFrame(int l, int t, int r, int b) {
		mWidth = r - l;
		mHeight = b - t;

		mMatrix.reset();
		mScale = (float) r / (float) mIntrinsicWidth;
		int paddingHeight = 0;
		int paddingWidth = 0;
		if (mScale * mIntrinsicHeight > mHeight) {
			mScale = (float) mHeight / (float) mIntrinsicHeight;
			mMatrix.postScale(mScale, mScale);
			paddingWidth = (r - mWidth) / 2;
			paddingHeight = 0;
		} else {
			mMatrix.postScale(mScale, mScale);
			paddingHeight = (b - mHeight) / 2;
			paddingWidth = 0;
		}
		mMatrix.postTranslate(paddingWidth, paddingHeight);

		setImageMatrix(mMatrix);
		mMinScale = mScale;
		zoomTo(mScale, mWidth / 2, mHeight / 2);
		cutting();
		return super.setFrame(l, t, r, b);
	}

	protected float getValue(Matrix matrix, int whichValue) {
		matrix.getValues(mMatrixValues);
		return mMatrixValues[whichValue];
	}

	protected float getScale() {
		return getValue(mMatrix, Matrix.MSCALE_X);
	}

	protected float getTranslateX() {
		return getValue(mMatrix, Matrix.MTRANS_X);
	}

	protected float getTranslateY() {
		return getValue(mMatrix, Matrix.MTRANS_Y);
	}

	protected void maxZoomTo(int x, int y) {
		if (mMinScale != getScale() && (getScale() - mMinScale) > 0.1f) {
			// threshold 0.1f
			float scale = mMinScale / getScale();
			zoomTo(scale, x, y);
		} else {
			float scale = MAX_SCALE / getScale();
			zoomTo(scale, x, y);
		}
	}

	protected void zoomTo(float scale, int x, int y) {
		if (getScale() * scale < mMinScale) {
			return;
		}
		if (scale >= 1 && getScale() * scale > MAX_SCALE) {
			return;
		}
		mMatrix.postScale(scale, scale);
		// move to center
		mMatrix.postTranslate(-(mWidth * scale - mWidth) / 2, -(mHeight * scale - mHeight) / 2);

		// move x and y distance
		mMatrix.postTranslate(-(x - (mWidth / 2)) * scale, 0);
		mMatrix.postTranslate(0, -(y - (mHeight / 2)) * scale);
		setImageMatrix(mMatrix);
	}

	public boolean cutting() {
		boolean hasCut = false;
		int width = (int) (mIntrinsicWidth * getScale());
		int height = (int) (mIntrinsicHeight * getScale());
		if (getTranslateX() < -(width - mWidth)) {
			mMatrix.postTranslate(-(getTranslateX() + width - mWidth), 0);
			Log.d(TAG, "arriving border");
			isArrivingBorder = true;
			hasCut = true;
		}
		if (getTranslateX() > 0) {
			mMatrix.postTranslate(-getTranslateX(), 0);
			Log.d(TAG, "arriving border");
			isArrivingBorder = true;
			hasCut = true;
		}
		if (getTranslateY() < -(height - mHeight)) {
			mMatrix.postTranslate(0, -(getTranslateY() + height - mHeight));
			hasCut = true;
		}
		if (getTranslateY() > 0) {
			mMatrix.postTranslate(0, -getTranslateY());
			hasCut = true;
		}
		if (width < mWidth) {
			mMatrix.postTranslate((mWidth - width) / 2, 0);
			hasCut = true;
		}
		if (height < mHeight) {
			mMatrix.postTranslate(0, (mHeight - height) / 2);
			hasCut = true;
		}
		setImageMatrix(mMatrix);
		return hasCut;
	}

	private float distance(float x0, float x1, float y0, float y1) {
		float x = x0 - x1;
		float y = y0 - y1;
		return FloatMath.sqrt(x * x + y * y);
	}

	private float dispDistance() {
		return FloatMath.sqrt(mWidth * mWidth + mHeight * mHeight);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int touchCount = event.getPointerCount();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_1_DOWN:
		case MotionEvent.ACTION_POINTER_2_DOWN:
			Log.d(TAG, "action down");
			if (touchCount >= 2) {
				float distance = distance(event.getX(0), event.getX(1), event.getY(0), event.getY(1));
				mPrevDistance = distance;
				isScaling = true;
			} else {
				mPrevMoveX = (int) event.getX();
				mPrevMoveY = (int) event.getY();
			}
			return true;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, "action move");
			if (touchCount >= 2 && isScaling) {
				float dist = distance(event.getX(0), event.getX(1), event.getY(0), event.getY(1));
				float scale = (dist - mPrevDistance) / dispDistance();
				mPrevDistance = dist;
				scale += 1;
				scale = scale * scale;
				zoomTo(scale, mWidth / 2, mHeight / 2);
			} else if (!isScaling) {
				int distanceX = mPrevMoveX - (int) event.getX();
				int distanceY = mPrevMoveY - (int) event.getY();
				mPrevMoveX = (int) event.getX();
				mPrevMoveY = (int) event.getY();
				mMatrix.postTranslate(-distanceX, -distanceY);
			}
			return cutting();
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_1_UP:
		case MotionEvent.ACTION_POINTER_2_UP:
			Log.d(TAG, "action up");
			mPrevMoveX = (int) event.getX();
			mPrevMoveY = (int) event.getY();
			isScaling = false;
			return false;
			// break;
		default:
			Log.d(TAG, "default event");
			return false;
		}
		// return false;
	}

	public boolean isArrivingBorder() {
		return isArrivingBorder;
	}

}