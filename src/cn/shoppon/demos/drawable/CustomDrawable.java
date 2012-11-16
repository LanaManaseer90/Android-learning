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

	private int mWidths;
	private int mHeights;

	private float mLeft;
	private float mTop;

	private RectF mOval;

	private float mStartAngle;

	private Map<Integer, Float> mRangeMap;
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
	 * ����Ĭ�ϻ���
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
	 * ����Բ���ı���
	 * 
	 * @param rangeMap
	 *            ����Map
	 */
	public void setRange(Map<Integer, Float> rangeMap) {
		this.mRangeMap = rangeMap;
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
		 * ��һ������������
		 */
		initRectF(mLeft, mTop, mLeft + mWidths, mTop + mHeights);

		/**
		 * �ڶ�������Բ
		 */
		canvas.drawCircle(mLeft + mWidths / 2, mTop + mHeights / 2, mHeights / 2, mBackPaint);

		/**
		 * ����Բ��
		 */
		initPaint();
		drawRangeArcs(canvas, mOval, mRangeMap, mPaints);

		/**
		 * ���Ĳ���СԲ
		 */
		canvas.drawCircle(mWidths / 2, mHeights / 2, mWidths / 4, mTextBackPaint);

		/**
		 * ���岽����
		 */
		canvas.drawText("ʣ��", mWidths * 0.375f, mHeights * 0.375f, mTextPaint);
		canvas.drawText("30%", mWidths * 0.375f, mHeights * 0.625f, mTextPaint);
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
	 * ��ݱ���ϼ���ƫ��Ƕ�
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
