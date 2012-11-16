/**
 * 
 */
package cn.shoppon.demos.ui;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import cn.shoppon.demos.R;
import cn.shoppon.demos.model.CheckAdapter;

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
public class CheckBoxInListView extends Activity implements OnClickListener {
	private CheckAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_list);

		ListView listView = (ListView) findViewById(R.id.check_listview);
		List<String> items = Arrays.asList("one", "two", "three");
		mAdapter = new CheckAdapter(this, items);
		listView.setAdapter(mAdapter);

		findViewById(R.id.select_all).setOnClickListener(this);
		findViewById(R.id.unselect_all).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.select_all) {
			mAdapter.selectAll(true);
		} else if (id == R.id.unselect_all) {
			mAdapter.selectAll(false);
		}
	}

}
