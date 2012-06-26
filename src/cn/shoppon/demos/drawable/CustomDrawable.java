/**
 * 
 */
package cn.shoppon.demos.drawable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;

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
public class CustomDrawable extends Drawable {
	private static final String TAG = "CustomDrawable";

	public static int COLOR_GRAY = Color.rgb(204, 204, 204);
	public static int COLOR_GREEN = Color.rgb(1, 153, 52);
	public static int COLOR_YELLOW = Color.rgb(255, 255, 103);
	public static int COLOR_GREP = Color.rgb(205, 102, 255);
	public static int COLOR_ORANGE = Color.rgb(254, 153, 0);

	private Paint[] mPaints;

	private Paint mBackPaint;
	private Paint mTextBackPaint;
	private Paint mTextPaint;

	/** 圆弧的宽度 **/
	private int mWidths;
	/** 圆弧的高度 **/
	private int mHeights;

	private float mLeft;
	private float mTop;
	private float mRight;
	private float mBottom;

	/** 圆所在的矩形 **/
	private RectF mOval;

	private float mStartAngle;

	/** 比例Map **/
	private Map<Integer, Float> mRangeMap;
	/** 颜色Map **/
	private Map<Integer, Integer> mColorMap;

	public CustomDrawable() {
		mWidths = 300;
		mHeights = 300;

		initDefaultPaint();

		mPaints = new Paint[4];
		for (int i = 0; i < mPaints.length; i++) {
			mPaints[i] = new Paint();
		}
		mPaints[0].setColor(Color.BLUE);
		mPaints[1].setColor(Color.RED);
		mPaints[2].setColor(Color.GREEN);
		mPaints[3].setColor(Color.YELLOW);
	}

	public CustomDrawable(int widths, int heights) {
		mWidths = 300;
		mHeights = 300;

		initDefaultPaint();
		initRectF(0, 0, 300, 300);
	}

	/**
	 * 设置默认画笔
	 */
	private void initDefaultPaint() {
		mBackPaint = new Paint();
		mBackPaint.setAntiAlias(true);
		mBackPaint.setColor(COLOR_GRAY);

		mTextBackPaint = new Paint();
		mTextBackPaint.setAntiAlias(true);
		mTextBackPaint.setColor(Color.WHITE);

		mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
		mTextPaint.setColor(Color.BLACK);
		mTextPaint.setTextSize(30f);
	}

	/**
	 * 设置圆弧的比例
	 * 
	 * @param rangeMap
	 *            比例Map
	 */
	public void setRange(Map<Integer, Float> rangeMap) {
		this.mRangeMap = rangeMap;
	}

	/**
	 * 设置背景颜色
	 * 
	 * @param color
	 */
	public void setBackground(int color) {
		if (mBackPaint != null) {
			mBackPaint.setColor(color);
		}
	}

	/**
	 * 设置颜色
	 * 
	 * @param colorMap
	 */
	public void setColor(Map<Integer, Integer> colorMap) {
		this.mColorMap = colorMap;
	}

	/**
	 * 设置范围
	 * 
	 * @param type
	 * @param range
	 */
	public void setRange(int type, float range) {
		mRangeMap.put(type, range);
	}

	/**
	 * 设置单种分类颜色
	 * 
	 * @param type
	 * @param color
	 */
	public void setColor(int type, int color) {
		mColorMap.put(type, color);
	}

	/**
	 * 设置起始偏移角度
	 * 
	 * @param startAngle
	 */
	public void setStartAngle(float startAngle) {
		mStartAngle = startAngle;
	}

	/**
	 * 初始化圆弧所在的圆
	 * 
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	private void initRectF(float left, float top, float right, float bottom) {
		this.mOval = new RectF(left, top, right, bottom);
	}

	/**
	 * 初始化画笔
	 */
	private void initPaint() {
		if (mColorMap == null) {
			throw new NullPointerException("color map has not been inited");
		}
		int size = mColorMap.size();
		if (size <= 0) {
			throw new IllegalStateException("do you forget init color map?");
		}

		mPaints = new Paint[size];
		for (int i = 0; i < mColorMap.size(); i++) {
			mPaints[i] = new Paint();
			mPaints[i].setAntiAlias(true);
			mPaints[i].setColor(mColorMap.get(i));
		}
	}

	@Override
	public void draw(Canvas canvas) {
		Log.d(TAG, "draw");

		/**
		 * 第一步画背景矩形
		 */
		initRectF(mLeft, mTop, mLeft + mWidths, mTop + mHeights);

		/**
		 * 第二步画大圆
		 */
		canvas.drawCircle(mLeft + mWidths / 2, mTop + mHeights / 2, mHeights / 2, mBackPaint);

		/**
		 * 第三步画圆弧
		 */
		initPaint();
		drawRangeArcs(canvas, mOval, mRangeMap, mPaints);

		/**
		 * 第四步画小圆
		 */
		canvas.drawCircle(mWidths / 2, mHeights / 2, mWidths / 4, mTextBackPaint);

		/**
		 * 第五步画字
		 */
		canvas.drawText("剩余", mWidths * 0.375f, mHeights * 0.375f, mTextPaint);
		canvas.drawText("30%", mWidths * 0.375f, mHeights * 0.625f, mTextPaint);
	}

	/**
	 * 根据比例和偏移比例画弧形区域
	 * 
	 * @param canvas
	 *            画布
	 * @param oval
	 *            弧形所在矩形区域
	 * @param ranges
	 *            每个弧形比例
	 * @param angles
	 *            偏移角度
	 * @param paints
	 *            每个弧形区域的画笔
	 */
	private void drawRangeArcs(Canvas canvas, RectF oval, Map<Integer, Float> rangeMap, Paint[] paints) {
		if (rangeMap.size() != paints.length) {
			throw new IllegalArgumentException("color map and range map must be the same size");
		}

		float[] angles = measureAngle(rangeMap.values());

		for (int i = 0; i < angles.length; i++) {
			canvas.drawArc(oval, angles[i], rangeMap.get(i) * 3.6f, true, paints[i]);
		}
	}

	/**
	 * 根据比例集合计算偏离角度
	 * 
	 * @param ranges
	 * @return
	 */
	private float[] measureAngle(Collection<Float> ranges) {
		if (ranges.size() <= 0) {
			throw new IllegalArgumentException("range collection cannot be empty");
		}
		float[] result = new float[ranges.size()];
		result[0] = mStartAngle;
		float sum = 0;
		int i = 1;
		for (Iterator<Float> iterator = ranges.iterator(); iterator.hasNext();) {
			if (i == ranges.size()) {
				break;
			}
			sum += iterator.next();
			result[i++] = mStartAngle + sum * 3.6f;
		}
		return result;
	}

	@Override
	public void setAlpha(int alpha) {

	}

	@Override
	public void setColorFilter(ColorFilter cf) {

	}

	@Override
	public int getOpacity() {
		return 0;
	}

}
