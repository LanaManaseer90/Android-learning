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

	private float mWidths;
	private float mHeights;

	private int mTextSize;

	private RectF mOval;

	private float mStartAngle;

	private Map<Integer, Float> mRangeMap;
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
	 * ����Ĭ�ϻ���
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
	 * ����Բ���ı���
	 * 
	 * @param rangeMap
	 *            ����Map
	 */
	public void setRange(Map<Integer, Float> rangeMap) {
		this.mRangeMap = rangeMap;
		invalidate();
	}

	/**
	 * ���ñ�����ɫ
	 * 
	 * @param color
	 */
	public void setBackground(int color) {
		if (mBackPaint != null) {
			mBackPaint.setColor(color);
		}
	}

	/**
	 * ������ɫ
	 * 
	 * @param colorMap
	 */
	public void setColor(Map<Integer, Integer> colorMap) {
		this.mColorMap = colorMap;
		invalidate();
	}

	/**
	 * ���÷�Χ
	 * 
	 * @param type
	 * @param range
	 */
	public void setRange(int type, float range) {
		mRangeMap.put(type, range);
	}

	/**
	 * ���õ��ַ�����ɫ
	 * 
	 * @param type
	 * @param color
	 */
	public void setColor(int type, int color) {
		mColorMap.put(type, color);
	}

	/**
	 * ������ʼƫ�ƽǶ�
	 * 
	 * @param startAngle
	 */
	public void setStartAngle(float startAngle) {
		mStartAngle = startAngle;
	}

	/**
	 * ��ʼ��Բ�����ڵ�Բ
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
	 * ��ʼ������
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
		 * ��һ������������
		 */
		initRectF(0, 0, mWidths, mHeights);

		/**
		 * �ڶ�������Բ
		 */
		canvas.drawCircle(mWidths / 2, mHeights / 2, mWidths / 2, mBackPaint);

		/**
		 * ����Բ��
		 */
		initPaint(mColorMap);
		drawRangeArcs(canvas, mOval, mRangeMap, mPaintMap);

		/**
		 * ���Ĳ���СԲ
		 */
		canvas.drawCircle(mWidths / 2, mHeights / 2, mWidths / 5, mTextBackPaint);

		/**
		 * ���岽����
		 */
		mTextPaint.setTextSize(mTextSize);
		mTextPaint.measureText("�ڰ�");
	}

	/**
	 * ��ݱ����ƫ�Ʊ���������
	 * 
	 * @param canvas
	 *            ����
	 * @param oval
	 *            �������ھ�������
	 * @param ranges
	 *            ÿ�����α���
	 * @param angles
	 *            ƫ�ƽǶ�
	 * @param paints
	 *            ÿ����������Ļ���
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
	 * ��ݱ���ϼ���ƫ��Ƕ�
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
