package cn.shoppon.demos.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;
import cn.shoppon.demos.R;

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
public class PopupMenu extends Activity implements OnClickListener {
	private PopupWindow menuWindow;

	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_MENU) {

			if (!menuWindow.isShowing()) {
				menuWindow.showAtLocation(this.findViewById(R.id.main), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
			} else {
				menuWindow.dismiss();
			}
			return true;

		} else if (keyCode == KeyEvent.KEYCODE_BACK && menuWindow.isShowing()) {
			menuWindow.dismiss();
			return true;
		}

		return super.onKeyDown(keyCode, event);

	}

	private void initPopupMenu() {
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.menu, null);

		menuWindow = new PopupWindow(layout, LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

		layout.findViewById(R.id.layout_start).setOnClickListener(this);
		layout.findViewById(R.id.layout_pause).setOnClickListener(this);
		layout.findViewById(R.id.layout_stop).setOnClickListener(this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.popupmenu);

		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		initPopupMenu();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.layout_start:
			Toast.makeText(PopupMenu.this, "click start", Toast.LENGTH_LONG).show();
			break;
		case R.id.layout_pause:
			Toast.makeText(PopupMenu.this, "click pause", Toast.LENGTH_LONG).show();
			break;
		case R.id.layout_stop:
			Toast.makeText(PopupMenu.this, "click stop", Toast.LENGTH_LONG).show();
			break;
		}
	}

}
