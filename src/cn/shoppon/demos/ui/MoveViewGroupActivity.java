package cn.shoppon.demos.ui;

import android.app.Activity;
import android.os.Bundle;
import cn.shoppon.demos.view.Workspace;

public class MoveViewGroupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new Workspace(this));
	}

}
