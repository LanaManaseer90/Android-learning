package cn.shoppon.demos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import cn.shoppon.demos.animation.AnimationSrcActivity;
import cn.shoppon.demos.breakdownload.BreakDownloadActivity;
import cn.shoppon.demos.contact.ContactActivity;
import cn.shoppon.demos.image.ImageBrowseActivity;
import cn.shoppon.demos.ui.AppSizeActivity;
import cn.shoppon.demos.ui.CheckBoxInListView;
import cn.shoppon.demos.ui.CustomViewActivity;
import cn.shoppon.demos.ui.ExpandableListActivity;
import cn.shoppon.demos.ui.ImageListActivity;
import cn.shoppon.demos.ui.MoveViewGroupActivity;
import cn.shoppon.demos.ui.PopupMenu;
import cn.shoppon.demos.ui.Pull;
import cn.shoppon.demos.ui.TaskManager;

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
public class MainActivity extends Activity implements OnClickListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		findViewById(R.id.pull).setOnClickListener(this);
		findViewById(R.id.popup_menu).setOnClickListener(this);
		findViewById(R.id.task_manager).setOnClickListener(this);
		findViewById(R.id.image_download).setOnClickListener(this);
		findViewById(R.id.app_size).setOnClickListener(this);
		findViewById(R.id.move_view_group).setOnClickListener(this);
		findViewById(R.id.check_list).setOnClickListener(this);
		findViewById(R.id.expandable_list).setOnClickListener(this);
		findViewById(R.id.animation_activity).setOnClickListener(this);
		findViewById(R.id.custom_view).setOnClickListener(this);
		findViewById(R.id.contact).setOnClickListener(this);
		findViewById(R.id.image_browse).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent = new Intent();
		Class<?> c = null;
		switch (id) {
		case R.id.pull:
			c = Pull.class;
			break;
		case R.id.popup_menu:
			c = PopupMenu.class;
			break;
		case R.id.task_manager:
			c = TaskManager.class;
			break;
		case R.id.image_download:
			c = ImageListActivity.class;
			break;
		case R.id.app_size:
			c = AppSizeActivity.class;
			break;
		case R.id.move_view_group:
			c = MoveViewGroupActivity.class;
			break;
		case R.id.check_list:
			c = CheckBoxInListView.class;
			break;
		case R.id.expandable_list:
			c = ExpandableListActivity.class;
			break;
		case R.id.animation_activity:
			c = AnimationSrcActivity.class;
			break;
		case R.id.break_download:
			c = BreakDownloadActivity.class;
			break;
		case R.id.custom_view:
			c = CustomViewActivity.class;
			break;
		case R.id.contact:
			c = ContactActivity.class;
			break;
		case R.id.image_browse:
			c = ImageBrowseActivity.class;
			break;
		}
		if (c != null) {
			intent.setClass(this, c);
			startActivity(intent);
		}
	}
}