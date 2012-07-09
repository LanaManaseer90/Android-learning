/**
 * 
 */
package cn.shoppon.demos.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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
public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	private ScaleImageView mCurrentView;
	private String[] mFiles;

	public ImageAdapter(Context context) {
		mContext = context;
		mFiles = new String[5];
		mFiles[0] = "/mnt/sdcard/8684/a0.jpg";
		mFiles[1] = "/mnt/sdcard/8684/a1.jpg";
		mFiles[2] = "/mnt/sdcard/8684/a2.jpg";
		mFiles[3] = "/mnt/sdcard/8684/a3.jpg";
		mFiles[4] = "/mnt/sdcard/8684/a4.jpg";
	}

	@Override
	public int getCount() {
		return 5;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		mCurrentView = new ScaleImageView(mContext);
		Bitmap bitmap = BitmapFactory.decodeFile(mFiles[position]);
		mCurrentView.setImageBitmap(bitmap);
		return mCurrentView;
	}

	public ScaleImageView getCurrentView() {
		return mCurrentView;
	}

}
