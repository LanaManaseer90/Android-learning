package cn.shoppon.demos.view;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

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
public class CustomView extends View {
	private static final String TAG = "CustomView";

	public static int COLOR_GRAY = Color.rgb(204, 204, 204);
	public static int COLOR_GREEN = Color.rgb(1, 153, 52);
	public static int COLOR_YELLOW = Color.rgb(255, 255, 103);
	public static int COLOR_GREP = Color.rgb(205, 102, 255);
	public static int COLOR_ORANGE = Color.rgb(254, 153, 0);

	private Map<Integer, Paint> mPaintMap;

	private Paint mBackPaint;
	private Paint mTextBackPaint;
	private TextPaint mTextPaint;

	/** VIEW的宽度 **/
	private float mWidths;
	/** VIEW的高度 **/
	private float mHeights;

	private int mTextSize;
	private int mTextColor;

	/** 圆所在的矩形 **/
	private RectF mOval;

	private float mStartAngle;

	/** 比例Map **/
	private Map<Integer, Float> mRangeMap;
	/** 颜色Map **/
	private Map<Integer, Integer> mColorMap;

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);

		initDefaultPaint();

		mOval = new RectF();
		mRangeMap = new HashMap<Integer, Float>();
		mColorMap = new HashMap<Integer, Integer>();
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		return super.dispatchTouchEvent(event);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		int measuredWidth = widthSize;
		int measuredHeight = heightSize;

		switch (widthMode) {
		case MeasureSpec.EXACTLY:
			break;
		case MeasureSpec.AT_MOST:
			break;
		case MeasureSpec.UNSPECIFIED:
			break;
		}

		switch (heightMode) {
		case MeasureSpec.EXACTLY:
			break;
		case MeasureSpec.AT_MOST:
			break;
		case MeasureSpec.UNSPECIFIED:
			break;
		}

		setMeasuredDimension(measuredWidth, measuredHeight);
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

		mTextPaint = new TextPaint();
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
		invalidate();
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
		invalidate();
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
	private void initPaint(Map<Integer, Integer> colorMap) {
		if (colorMap == null) {
			throw new NullPointerException("color map has not been inited");
		}

		mPaintMap = new HashMap<Integer, Paint>();
		for (Integer item : colorMap.keySet()) {
			Paint tmp = new Paint();
			tmp.setAntiAlias(true);
			tmp.setColor(colorMap.get(item));
			mPaintMap.put(item, tmp);
		}
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.d(TAG, "draw");

		mWidths = getWidth();
		mHeights = getHeight();

		int[] location = new int[2];
		getLocationInWindow(location);
		// getLocationOnScreen(location);

		/**
		 * 第一步画背景矩形
		 */
		initRectF(0, 0, mWidths, mHeights);

		/**
		 * 第二步画大圆
		 */
		canvas.drawCircle(mWidths / 2, mHeights / 2, mWidths / 2, mBackPaint);

		/**
		 * 第三步画圆弧
		 */
		initPaint(mColorMap);
		drawRangeArcs(canvas, mOval, mRangeMap, mPaintMap);

		/**
		 * 第四步画小圆
		 */
		canvas.drawCircle(mWidths / 2, mHeights / 2, mWidths / 5, mTextBackPaint);

		/**
		 * 第五步画字
		 */
		mTextPaint.setTextSize(mTextSize);
		mTextPaint.measureText("第八");
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
	private void drawRangeArcs(Canvas canvas, RectF oval, Map<Integer, Float> rangeMap, Map<Integer, Paint> paintMap) {
		if (rangeMap.size() != paintMap.size()) {
			throw new IllegalArgumentException("color map and range map must be the same size");
		}

		Map<Integer, Float> angles = measureAngle(rangeMap);

		for (Integer item : rangeMap.keySet()) {
			canvas.drawArc(oval, angles.get(item), rangeMap.get(item) * 3.6f, true, paintMap.get(item));
		}

	}

	/**
	 * 根据比例集合计算偏离角度
	 * 
	 * @param ranges
	 * @return
	 */
	private Map<Integer, Float> measureAngle(Map<Integer, Float> rangeMap) {
		Map<Integer, Float> result = new HashMap<Integer, Float>();
		float sum = 0;
		for (Integer item : rangeMap.keySet()) {
			result.put(item, mStartAngle + sum * 3.6f);
			sum += rangeMap.get(item);
		}
		return result;
	}

}
