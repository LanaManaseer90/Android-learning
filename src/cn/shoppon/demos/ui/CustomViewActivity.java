package cn.shoppon.demos.ui;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import cn.shoppon.demos.R;
import cn.shoppon.demos.drawable.CustomDrawable;
import cn.shoppon.demos.view.CustomView;

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
public class CustomViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_view);

		ImageView imageView = (ImageView) findViewById(R.id.custom_view_arc);
		int width = imageView.getWidth();
		int height = imageView.getHeight();
		CustomDrawable customDrawable = new CustomDrawable(width, height);

		Map<Integer, Integer> colorMap = new HashMap<Integer, Integer>();
		colorMap.put(0, CustomDrawable.COLOR_GREEN);
		colorMap.put(1, CustomDrawable.COLOR_ORANGE);
		colorMap.put(2, CustomDrawable.COLOR_GREP);
		colorMap.put(3, CustomDrawable.COLOR_YELLOW);
		customDrawable.setColor(colorMap);

		Map<Integer, Float> rangeMap = new HashMap<Integer, Float>();
		rangeMap.put(0, 10f);
		rangeMap.put(1, 20f);
		rangeMap.put(2, 15f);
		rangeMap.put(3, 40f);
		customDrawable.setRange(rangeMap);

		imageView.setImageDrawable(customDrawable);

		CustomView customView = (CustomView) findViewById(R.id.custom_view_by_view);
		customView.setRange(rangeMap);
		customView.setColor(colorMap);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d("custom", "x-->" + event.getX() + "y-->" + event.getY());
		return super.onTouchEvent(event);
	}

}
