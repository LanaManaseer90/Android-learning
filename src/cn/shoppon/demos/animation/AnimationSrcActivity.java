/**
 * 
 */
package cn.shoppon.demos.animation;

import cn.shoppon.demos.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

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
public class AnimationSrcActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_src);
		findViewById(R.id.animation_fade).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.animation_fade) {
			startActivity(new Intent().setClass(getApplicationContext(), AnimationDestActivity.class));
			overridePendingTransition(android.R.anim.fade_out, android.R.anim.fade_in);
		}
	}

}
