/**
 * 
 */
package cn.shoppon.demos.image;

import cn.shoppon.demos.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

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
public class ImageBrowseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_browse);

		ImageGallary imageGallary = (ImageGallary) findViewById(R.id.image_gallary);
		ImageAdapter adapter = new ImageAdapter(this);
		imageGallary.setAdapter(adapter);
	}

}
