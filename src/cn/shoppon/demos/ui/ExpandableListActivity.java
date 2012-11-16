/**
 * 
 */
package cn.shoppon.demos.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import cn.shoppon.demos.R;
import cn.shoppon.demos.model.CommonExpandableAdapter;

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
public class ExpandableListActivity extends Activity {

	private ExpandableListView mExpandableListView;
	private CommonExpandableAdapter mCommonExpandableAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandablelist);

		mExpandableListView = (ExpandableListView) findViewById(R.id.expandablelist);

		List<String> groups = Arrays.asList("one", "two", "three");
		List<List<String>> children = new ArrayList<List<String>>();
		List<String> tmp = Arrays.asList("one", "two", "three");
		children.add(tmp);
		children.add(tmp);
		children.add(tmp);
		mCommonExpandableAdapter = new CommonExpandableAdapter(this, groups, children);
		mExpandableListView.setAdapter(mCommonExpandableAdapter);
	}
}
